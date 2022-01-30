package com.example.demo.transfers;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.security.priviledges.UserPriviledges;
import com.example.demo.user.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class SqlTransferRepository implements TransferRepository{


    private final DataSource dataSource;

    public SqlTransferRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Transfer getTransfer(String transferId) {


        Transfer transfer = null;
        try {
            Connection comm = dataSource.getConnection();
            PreparedStatement preparedStatement = comm.prepareStatement("SELECT * FROM Transfer where id = ?");
            preparedStatement.setString(1,transferId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
               transfer = new Transfer(resultSet.getString("id"), resultSet.getString("senderId"),
                      resultSet.getString("receiverId"), BigDecimal.valueOf(Double.parseDouble(resultSet.getString("amount"))));
                        transfer.setTransferDate(Date.valueOf(resultSet.getString("transferDate")));

            }
            comm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return transfer;
        }


    }

    @Override
    public void addTransfer(Transfer transfer) {

        try {
            Connection comm = dataSource.getConnection();
            PreparedStatement preparedStatement = comm.prepareStatement("INSERT INTO Transfer VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, transfer.getId());
            preparedStatement.setString(2, transfer.getSenderId());
            preparedStatement.setString(3, transfer.getReceiverId());
            preparedStatement.setString(4, String.valueOf(transfer.getAmount()));
            preparedStatement.setString(5, transfer.getTransferDate().toString());

            preparedStatement.execute();
            comm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Transfer> getAccountsTransfers(BankAccount bankAccount) {
        List<Transfer> transfers = new ArrayList<>();
        try {
            Connection comm = dataSource.getConnection();

            PreparedStatement preparedStatement = comm.prepareStatement("SELECT * FROM Transfer where senderId = ? or receiverId = ? ");
            preparedStatement.setString(1, bankAccount.getAccountNumber());
            preparedStatement.setString(2, bankAccount.getAccountNumber());

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                        Transfer transfer = new Transfer(resultSet.getString("id"), resultSet.getString("senderId"),
                        resultSet.getString("receiverId"), BigDecimal.valueOf(Double.parseDouble(resultSet.getString("amount"))));
                        transfer.setTransferDate(Date.valueOf(resultSet.getString("transferDate")));
                        transfers.add(transfer);
            }
            comm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transfers;
    }
}
