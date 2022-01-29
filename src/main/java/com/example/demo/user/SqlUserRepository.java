package com.example.demo.user;



import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankEntityStatus;
import com.example.demo.card.Card;
import com.example.demo.security.priviledges.UserPriviledges;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Repository
public class SqlUserRepository implements UserRepository {


    private final DataSource dataSource;



    public SqlUserRepository(DataSource dataSource) {
        this.dataSource = dataSource;

    }


    @Override
    public User getUser(String login) {
        User user = new User();
        user.setUserPriviledges(UserPriviledges.getNoPriviledges());
        user.setAccounts(new ArrayList<>());
        try {
            Connection comm = dataSource.getConnection();
            PreparedStatement preparedStatement = comm.prepareStatement("SELECT * FROM TUser where login = ?");
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                user.setLogin(resultSet.getString("login"));
                user.setPass(resultSet.getString("tpass"));
                String role = resultSet.getString("trole");
                if("admin".equals(role)) {
                    user.setUserPriviledges(UserPriviledges.getAdminPriviledges());
                } else if("employee".equals(role)) {
                    user.setUserPriviledges(UserPriviledges.getEmployeePriviledges());
                } else if("user".equals(role)) {
                    user.setUserPriviledges(UserPriviledges.getClientPriviledges());
                }

                user.setAccounts(getBankAccounts(comm).stream().filter(e->e.getUser().getLogin().equals(user.getLogin())).collect(Collectors.toList()));
            }
            comm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return user;
        }

    }


    public List<BankAccount> getBankAccounts(Connection comm) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        try {



            PreparedStatement preparedStatement = comm.prepareStatement("SELECT * FROM BankAccount ");

            ResultSet resultSet = preparedStatement.executeQuery();



            while(resultSet.next())
            {

                BankAccount bankAccount = new BankAccount();
                bankAccount.setAccountNumber(resultSet.getString("accountNumber"));
                bankAccount.setStatus(BankEntityStatus.valueOf(resultSet.getString("status").toUpperCase(Locale.ROOT)));
                bankAccount.setBalance(BigDecimal.valueOf(Double.parseDouble(resultSet.getString("balance"))));
                bankAccount.setCurrency(resultSet.getString("currency"));
                bankAccount.setUser(getUser(resultSet.getString("login")));

                bankAccount.setCardList(new ArrayList<>());
                PreparedStatement preparedStatement2 = comm.prepareStatement("SELECT  * FROM Card where accountNumber = ?");
                preparedStatement2.setString(1, bankAccount.getAccountNumber());
                ResultSet resultSet2 = preparedStatement2.executeQuery();

                while(resultSet2.next())
                {
                    Card card = new Card(resultSet2.getString("cardNumber"), Integer.parseInt(resultSet2.getString("pin")),
                            BankEntityStatus.valueOf(resultSet2.getString("status").toUpperCase(Locale.ROOT)),
                            bankAccount);

                    bankAccount.getCardList().add(card);
                }

                bankAccounts.add(bankAccount);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }




        return bankAccounts;
    }

    @Override
    public void insertUser(User user) {

    }

    @Override
    public void deleteUser(String login) {

    }
}
