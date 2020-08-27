package com.epam.afshop.dao.impl;

import com.epam.afshop.dao.connection.ConnectionPool;
import com.epam.afshop.dao.CategoryDao;
import com.epam.afshop.entity.Category;

import java.sql.*;
import java.util.*;

public class CategoryDaoImpl implements CategoryDao {
    private static final String ADD_CATEGORY = "INSERT INTO category(name, language_id) VALUES (?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM category";
    private static final String SELECT_BY_ID = "SELECT id, name FROM category WHERE category.id = ?";
    private static final String UPDATE_CATEGORY = "UPDATE category SET name = ?, language_id = ? WHERE category.id = ?";
    private static final String DELETE_CATEGORY = "DELETE FROM category WHERE category.id = ?";

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    public CategoryDaoImpl() throws SQLException {
    }

    public boolean create(Category newCategory) {
        try (Connection connection = CONNECTION_POOL.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(ADD_CATEGORY);
            ps.setString(1, newCategory.getName());
            ps.setInt(2, newCategory.getLanguageId());
            ps.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Category> getAll() throws SQLException {
        List<Category> categories = new ArrayList<Category>();

        try (Connection con = CONNECTION_POOL.getConnection();
             Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                categories.add(new Category(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("language_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return categories;
        }
    }

    public Category getById(long id) {
        Category selectedCategory = null;
        try (Connection connection = CONNECTION_POOL.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                selectedCategory = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("language_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return selectedCategory;
        }
    }

    public boolean update(Category category) {
        try (Connection connection = CONNECTION_POOL.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(UPDATE_CATEGORY);
            ps.setString(1, category.getName());
            ps.setInt(2, category.getLanguageId());
            ps.setInt(3, category.getId());
            ResultSet rs = ps.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Category category) {
        try (Connection connection = CONNECTION_POOL.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(DELETE_CATEGORY);
            ps.setInt(1, category.getId());
            ResultSet rs = ps.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
