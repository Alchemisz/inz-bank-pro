package com.example.demo.user;


import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Repository
public class SqlUserRepository implements UserRepository {


    private final DataSource dataSource;



    public SqlUserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public User getUser(String login) {

        try {
            Connection comm = dataSource.getConnection();
            PreparedStatement preparedStatement = comm.prepareStatement("SELECT * FROM TUser");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int a = 2;
           /* PreparedStatement preparedStatement = comm.prepareStatement("SELECT * FROM TUser where login = ? ");
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            User user = new User();
            user.setLogin(resultSet.getString("login"));
            user.setPass(resultSet.getString("tpass"));

            System.out.println("Got user from sql : " + user.getLogin() + "  " + user.getPass());*/




        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public void insertUser(User user) {

    }

    @Override
    public void deleteUser(String login) {

    }
}
