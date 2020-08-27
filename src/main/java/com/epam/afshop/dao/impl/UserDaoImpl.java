package com.epam.afshop.dao.impl;

import com.epam.afshop.dao.UserDao;
import com.epam.afshop.dao.connection.ConnectionPool;
import com.epam.afshop.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final String ADD_USER = "INSERT INTO UserInfo(first_name, last_name, email, phone_number, password, userTypeId) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM UserInfo";
    private static final String SELECT_BY_ID = "SELECT * FROM UserInfo WHERE UserInfo.id = ?";
    private static final String SELECT_BY_EMAIL_AND_PASSWORD = "SELECT * FROM UserInfo WHERE UserInfo.email = ? AND UserInfo.password = ?";
    private static final String SELECT_BY_EMAIL = "SELECT * FROM UserInfo WHERE UserInfo.email = ?";
    private static final String UPDATE_USER = "UPDATE UserInfo SET first_name = ?, last_name = ?, email = ?, phone_number = ? password = ?, UserTypeId = ? WHERE UserInfo.id = ?";
    private static final String DELETE_USER = "DELETE FROM UserInfo WHERE UserInfo.id = ?";

    //TODO make select by login and password
    //TODO make login action

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public boolean create(User object) {
        try(Connection con = CONNECTION_POOL.getConnection()) {
            PreparedStatement ps = con.prepareStatement(ADD_USER);
            ps.setString(1, object.getFirstName());
            ps.setString(2, object.getLastName());
            ps.setString(3, object.getEmail());
            ps.setString(4, object.getPhoneNumber());
            ps.setString(5, object.getPassword());
            ps.setInt(6, object.getUserTypeId());
            ps.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        User user = null;
        try (Connection con = CONNECTION_POOL.getConnection();
             Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setPassword(resultSet.getString("password"));
                user.setUserTypeId(resultSet.getInt("user_type_id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return users;
        }
    }

    @Override
    public User getById(long id) {
        User selectedUser = null;
        try (Connection connection = CONNECTION_POOL.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                selectedUser = new User();
                selectedUser.setId(rs.getInt("id"));
                selectedUser.setFirstName(rs.getString("first_name"));
                selectedUser.setLastName(rs.getString("last_name"));
                selectedUser.setEmail(rs.getString("email"));
                selectedUser.setPhoneNumber(rs.getString("phone_number"));
                selectedUser.setPassword(rs.getString("password"));
                selectedUser.setUserTypeId(rs.getInt("user_type_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return selectedUser;
        }
    }

    @Override
    public boolean update(User user) {
        try (Connection connection = CONNECTION_POOL.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(UPDATE_USER);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhoneNumber());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.getUserTypeId());
            ps.setInt(7, user.getId());
            ResultSet rs = ps.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(User user) {
        try (Connection connection = CONNECTION_POOL.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(DELETE_USER);
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getByEmailAndPassword(String email, String password){
        User selectedUser = new User();

        try(Connection connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_BY_EMAIL_AND_PASSWORD)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            selectedUser.setId(rs.getInt("id"));
            selectedUser.setEmail(rs.getString("email"));
            selectedUser.setFirstName(rs.getString("first_name"));
            selectedUser.setLastName(rs.getString("last_name"));
            selectedUser.setPassword(rs.getString("password"));
            selectedUser.setPhoneNumber(rs.getString("phone_number"));
            selectedUser.setUserTypeId(rs.getInt("userTypeId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return (selectedUser.getId() != 0)? selectedUser:null;
        }
    }

    public User getByEmail(String email){
        User selectedUser = new User();

        try(Connection connection = CONNECTION_POOL.getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_BY_EMAIL)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            selectedUser.setId(rs.getInt("id"));
            selectedUser.setEmail(rs.getString("email"));
            selectedUser.setFirstName(rs.getString("first_name"));
            selectedUser.setLastName(rs.getString("last_name"));
            selectedUser.setPassword(rs.getString("password"));
            selectedUser.setPhoneNumber(rs.getString("phone_number"));
            selectedUser.setUserTypeId(rs.getInt("userTypeId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return (selectedUser.getId() != 0)? selectedUser:null;
        }
    }
}
