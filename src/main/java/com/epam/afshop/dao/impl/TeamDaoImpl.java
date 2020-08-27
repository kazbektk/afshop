package com.epam.afshop.dao.impl;

import com.epam.afshop.dao.TeamDao;
import com.epam.afshop.dao.connection.ConnectionPool;
import com.epam.afshop.entity.Product;
import com.epam.afshop.entity.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDaoImpl implements TeamDao {
    private static final String ADD_TEAM = "INSERT INTO team(name, logo) VALUES (?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM team";
    private static final String SELECT_BY_ID = "SELECT * FROM team WHERE team.id=?";
    private static final String UPDATE_TEAM = "UPDATE product SET name=?, logo=?, WHERE id=?";
    private static final String DELETE_TEAM = "DELETE FROM team WHERE id = ?";

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public boolean create(Team team) {
        try (Connection connection = CONNECTION_POOL.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(ADD_TEAM);
            ps.setString(1, team.getName());
            ps.setBytes(2, team.getLogo());
            ps.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Team> getAll() {
        List<Team> teams = new ArrayList<>();
        Team team = null;

        try (Connection con = CONNECTION_POOL.getConnection();
             Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                team = new Team();
                team.setId(resultSet.getInt("id"));
                team.setName(resultSet.getString("name"));
                team.setLogo(resultSet.getBytes("logo"));

                teams.add(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return teams;
        }
    }

    @Override
    public Team getById(long id) {
        Team selectedTeam = null;
        try (Connection connection = CONNECTION_POOL.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                selectedTeam = new Team();
                selectedTeam.setId(rs.getInt("id"));
                selectedTeam.setName(rs.getString("name"));
                selectedTeam.setLogo(rs.getBytes("logo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return selectedTeam;
        }
    }

    @Override
    public boolean update(Team team) {
        try (Connection connection = CONNECTION_POOL.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(UPDATE_TEAM);
            ps.setString(1, team.getName());
            ps.setBytes(2, team.getLogo());
            ps.setInt(3, team.getId());
            ResultSet rs = ps.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Team team) {
        try (Connection connection = CONNECTION_POOL.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(DELETE_TEAM);
            ps.setInt(1, team.getId());
            ResultSet rs = ps.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
