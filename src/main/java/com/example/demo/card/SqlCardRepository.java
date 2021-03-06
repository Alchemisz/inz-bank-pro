package com.example.demo.card;

import com.example.demo.bankAccount.BankAccountRepository;
import com.example.demo.bankAccount.BankEntityStatus;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

@Repository
public class SqlCardRepository implements CardRepository{

    private  BankAccountRepository bankAccountRepository;
    private final DataSource dataSource;

    public SqlCardRepository(BankAccountRepository bankAccountRepository, DataSource dataSource) {
        this.bankAccountRepository = bankAccountRepository;
        this.dataSource = dataSource;
    }


    @Override
    public Card getCard(String cardNumber) {

        Card card = null;

        try {
            Connection comm = dataSource.getConnection();
            PreparedStatement preparedStatement = comm.prepareStatement("SELECT * FROM CARD where cardNumber = ?");
            preparedStatement.setString(1, cardNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                        card = new Card(resultSet.getString("cardNumber"), Integer.parseInt(resultSet.getString("pin")),
                        BankEntityStatus.valueOf(resultSet.getString("status").toUpperCase(Locale.ROOT)),
                        bankAccountRepository.getBankAccount(resultSet.getString("accountNumber")));
            }
            comm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return card;
    }

    @Override
    public void addCard(Card card) {

        try {
            Connection comm = dataSource.getConnection();
            PreparedStatement preparedStatement = comm.prepareStatement("INSERT INTO Card Values (?,?,?,?)");
            preparedStatement.setString(1, card.getCardNumber());
            preparedStatement.setInt(2, card.getPIN());
            preparedStatement.setString(3,String.valueOf(card.getStatus()));
            preparedStatement.setString(4, card.getBankAccount().getAccountNumber());



            preparedStatement.execute();
            comm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void deleteCard(Card card) {

        try {
            Connection comm = dataSource.getConnection();
            PreparedStatement preparedStatement = comm.prepareStatement("DELETE FROM Card where cardNumber = ?");
            preparedStatement.setString(1, card.getCardNumber());

            preparedStatement.execute();
            comm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public void update(Card card) {

        try {
            Connection comm = dataSource.getConnection();
            PreparedStatement preparedStatement = comm.prepareStatement("UPDATE Card set pin = ?, status = ?, accountNumber = ? where cardNumber = ? ");
            preparedStatement.setInt(1, card.getPIN());
            preparedStatement.setString(2, String.valueOf(card.getStatus()));
            preparedStatement.setString(3, card.getBankAccount().getAccountNumber());
            preparedStatement.setString(4, card.getCardNumber());

            preparedStatement.executeUpdate();
            comm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
