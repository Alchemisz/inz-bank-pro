package com.example.demo.user;


import com.example.demo.security.priviledges.UserPriviledges;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return user;
        }

    }

    @Override
    public void insertUser(User user) {

    }

    @Override
    public void deleteUser(String login) {

    }
}
