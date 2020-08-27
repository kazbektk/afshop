package com.epam.afshop.dao.impl;

import com.epam.afshop.dao.UserTypeDao;
import com.epam.afshop.dao.connection.ConnectionPool;
import com.epam.afshop.entity.User;
import com.epam.afshop.entity.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserTypeDaoImpl implements UserTypeDao {
    private static final String ADD_USER_TYPE = "INSERT INTO UserType(name, language_id) VALUES (?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM UserType";
    private static final String SELECT_BY_ID = "SELECT * FROM UserType WHERE UserType.id = ?";
    private static final String SELECT_BY_LOGIN_AND_PASSWORD = "SELECT * FROM UserInfo WHERE UserInfo.login = ? AND UserInfo.password = ?";
    private static final String UPDATE_USER_TYPE = "UPDATE UserType SET name = ?, languageId =? WHERE UserType.id = ?";
    private static final String DELETE_USER_TYPE = "DELETE FROM userType WHERE userType.id = ?";


    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public boolean create(UserType userType) {
        try(Connection con = CONNECTION_POOL.getConnection()) {
            PreparedStatement ps = con.prepareStatement(ADD_USER_TYPE);
            ps.setString(1, userType.getName());
            ps.setInt(2, userType.getLanguageId());
            ps.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<UserType> getAll() throws SQLException {
        List<UserType> userTypes = new ArrayList<>();

        try (Connection con = CONNECTION_POOL.getConnection();
             Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                userTypes.add(new UserType(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("languageId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return userTypes;
        }
    }

    @Override
    public UserType getById(long id) {
        UserType selectedUserType = null;
        try (Connection connection = CONNECTION_POOL.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                selectedUserType = new UserType(rs.getInt("id"), rs.getString("name"), rs.getInt("languageId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return selectedUserType;
        }
    }

    @Override
    public boolean update(UserType userType) {
        try (Connection connection = CONNECTION_POOL.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(UPDATE_USER_TYPE);
            ps.setString(1, userType.getName());
            ps.setInt(2, userType.getLanguageId());
            ps.setInt(3, userType.getId());
            ResultSet rs = ps.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(UserType userType) {
        try (Connection connection = CONNECTION_POOL.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(DELETE_USER_TYPE);
            ps.setInt(1, userType.getId());
            ResultSet rs = ps.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
