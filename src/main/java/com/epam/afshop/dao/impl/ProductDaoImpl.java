package com.epam.afshop.dao.impl;

import com.epam.afshop.dao.ProductDao;
import com.epam.afshop.dao.connection.ConnectionPool;
import com.epam.afshop.entity.Category;
import com.epam.afshop.entity.Product;
import com.epam.afshop.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private static final String ADD_PRODUCT = "INSERT INTO product(name, quantity, category_id, team_id, current_price) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM product";
    private static final String SELECT_BY_ID = "SELECT * FROM product WHERE id=?";
    private static final String UPDATE_PRODUCT = "UPDATE product SET name=?, quantity=?, category_id=?, team_id=?, current_price=? WHERE id=?";
    private static final String DELETE_PRODUCT = "DELETE FROM product WHERE product.id = ?";

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public boolean create(Product product) {
        try (Connection connection = CONNECTION_POOL.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(ADD_PRODUCT);
            ps.setString(1, product.getName());
            ps.setInt(2, product.getQuantity());
            ps.setInt(3, product.getCategoryId());
            ps.setInt(4, product.getTeamId());
            ps.setBigDecimal(5, product.getPrice());
            ps.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        Product product = null;

        try (Connection con = CONNECTION_POOL.getConnection();
             Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setCategoryId(resultSet.getInt("category_id"));
                product.setTeamId(resultSet.getInt("team_id"));
                product.setPrice(resultSet.getBigDecimal("current_price"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return products;
        }
    }

    @Override
    public Product getById(long id) {
        Product selectedProduct = null;
        try (Connection connection = CONNECTION_POOL.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                selectedProduct = new Product();
                selectedProduct.setId(rs.getInt("id"));
                selectedProduct.setName(rs.getString("name"));
                selectedProduct.setQuantity(rs.getInt("quantity"));
                selectedProduct.setCategoryId(rs.getInt("category_id"));
                selectedProduct.setTeamId(rs.getInt("team_id"));
                selectedProduct.setPrice(rs.getBigDecimal("current_price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return selectedProduct;
        }
    }

    @Override
    public boolean update(Product product) {
        try (Connection connection = CONNECTION_POOL.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(UPDATE_PRODUCT);
            ps.setString(1, product.getName());
            ps.setInt(2, product.getQuantity());
            ps.setInt(3, product.getCategoryId());
            ps.setInt(4, product.getTeamId());
            ps.setBigDecimal(5, product.getPrice());
            ps.setInt(6, product.getId());
            ResultSet rs = ps.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Product product) {
        try (Connection connection = CONNECTION_POOL.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(DELETE_PRODUCT);
            ps.setInt(1, product.getId());
            ResultSet rs = ps.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
