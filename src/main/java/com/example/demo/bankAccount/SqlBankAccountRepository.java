package com.example.demo.bankAccount;

import com.example.demo.card.Card;
import com.example.demo.security.priviledges.UserPriviledges;
import com.example.demo.transfers.Transfer;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
@Repository
public class SqlBankAccountRepository implements BankAccountRepository{


    private final DataSource dataSource;
    private UserRepository userRepository;

    public SqlBankAccountRepository(DataSource dataSource, UserRepository userRepository) {
        this.dataSource = dataSource;
        this.userRepository = userRepository;
    }


    @Override
    public BankAccount getBankAccount(String accountNUmber) {

        BankAccount bankAccount = null;

        try {
            Connection comm = dataSource.getConnection();
            PreparedStatement preparedStatement = comm.prepareStatement("SELECT * FROM BankAccount where accountNumber = ?");
            preparedStatement.setString(1, accountNUmber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                bankAccount = new BankAccount();
                bankAccount.setAccountNumber(resultSet.getString("accountNumber"));
                bankAccount.setStatus(BankEntityStatus.valueOf(resultSet.getString("status").toUpperCase(Locale.ROOT)));
                bankAccount.setBalance(BigDecimal.valueOf(Double.parseDouble(resultSet.getString("balance"))));
                bankAccount.setCurrency(resultSet.getString("currency"));
                //bankAccount.setUser(userRepository.getUser(resultSet.getString("login")));
                bankAccount.setUser(new User(resultSet.getString("login"), "", UserPriviledges.getNoPriviledges()));

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


            }
            comm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return bankAccount;
    }

    @Override
    public void register(BankAccount bankAccount) {

        try {
            Connection comm = dataSource.getConnection();
            PreparedStatement preparedStatement = comm.prepareStatement("INSERT INTO BankAccount VALUES (?,?,?,?,?)");
            preparedStatement.setString(1,  bankAccount.getAccountNumber());
            preparedStatement.setString(2, bankAccount.getStatus().toString());
            preparedStatement.setString(3, bankAccount.getBalance().toString());
            preparedStatement.setString(4, bankAccount.getCurrency());
            preparedStatement.setString(5, bankAccount.getUser().getLogin());

            preparedStatement.execute();
            comm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public List<BankAccount> getBankAccounts() {
        List<BankAccount> bankAccounts = new ArrayList<>();
        try {



            Connection comm = dataSource.getConnection();
            PreparedStatement preparedStatement = comm.prepareStatement("SELECT * FROM BankAccount ");

            ResultSet resultSet = preparedStatement.executeQuery();



            while(resultSet.next())
            {

                BankAccount bankAccount = new BankAccount();
                bankAccount.setAccountNumber(resultSet.getString("accountNumber"));
                bankAccount.setStatus(BankEntityStatus.valueOf(resultSet.getString("status").toUpperCase(Locale.ROOT)));
                bankAccount.setBalance(BigDecimal.valueOf(Double.parseDouble(resultSet.getString("balance"))));
                bankAccount.setCurrency(resultSet.getString("currency"));
                //bankAccount.setUser(userRepository.getUser(resultSet.getString("login")));
                bankAccount.setUser(new User(resultSet.getString("login"), "", UserPriviledges.getNoPriviledges()));

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
            comm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }




        return bankAccounts;
    }

    @Override
    public void update(BankAccount bankAccount) {

        try {
            Connection comm = dataSource.getConnection();
            PreparedStatement preparedStatement = comm.prepareStatement("UPDATE BankAccount set status = ?, balance = ?,  currency = ?,  login = ? where accountNumber = ?");



            preparedStatement.setString(1, String.valueOf(bankAccount.getStatus()));
            preparedStatement.setString(2, String.valueOf(bankAccount.getBalance()));
            preparedStatement.setString(3, bankAccount.getCurrency());
            preparedStatement.setString(4, bankAccount.getUser().getLogin());
            preparedStatement.setString(5, bankAccount.getAccountNumber());

            preparedStatement.executeUpdate();


            comm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
