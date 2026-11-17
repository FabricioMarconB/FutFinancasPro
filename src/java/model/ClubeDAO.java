package model;

import framework.config.AppConfig;
import framework.dao.DataAccessObject;
import framework.dao.DataBaseConnections;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ClubeDAO extends DataAccessObject<Clube> {

    @Override
    public void insert(Clube t) throws Exception {
        Connection connection = DataBaseConnections.getInstance().getConnection();
        String dml = "INSERT INTO clube (id, nome, sigla, cnpj, estadio, orcamento) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(dml);

        preparedStatement.setInt(1, t.getId());
        preparedStatement.setString(2, t.getNome());
        preparedStatement.setString(3, t.getSigla());
        preparedStatement.setString(4, t.getCnpj());
        preparedStatement.setString(5, t.getEstadio());
        preparedStatement.setDouble(6, t.getOrcamento());

        if (AppConfig.getInstance().getConfig("settings", "verbose").equals("true")) {
            System.out.println(preparedStatement);
        }

        preparedStatement.execute();
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
    }

    @Override
    public void update(Clube t) throws Exception {
        Connection connection = DataBaseConnections.getInstance().getConnection();
        String dml = "UPDATE clube SET nome = ?, sigla = ?, cnpj = ?, estadio = ?, orcamento = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(dml);

        preparedStatement.setString(1, t.getNome());
        preparedStatement.setString(2, t.getSigla());
        preparedStatement.setString(3, t.getCnpj());
        preparedStatement.setString(4, t.getEstadio());
        preparedStatement.setDouble(5, t.getOrcamento());
        preparedStatement.setInt(6, t.getId());

        if (AppConfig.getInstance().getConfig("settings", "verbose").equals("true")) {
            System.out.println(preparedStatement);
        }

        preparedStatement.execute();
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
    }

    @Override
    public void delete(Clube t) throws Exception {
        Connection connection = DataBaseConnections.getInstance().getConnection();
        String dml = "DELETE FROM clube WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(dml);

        preparedStatement.setInt(1, t.getId());

        if (AppConfig.getInstance().getConfig("settings", "verbose").equals("true")) {
            System.out.println(preparedStatement);
        }

        preparedStatement.execute();
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
    }

    @Override
    public Clube getUnique(Object... values) throws Exception {
        Clube resultado = null;
        String dql = "SELECT * FROM clube WHERE id = ?";
        Connection connection = DataBaseConnections.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(dql);

        preparedStatement.setInt(1, (int) values[0]);

        if (AppConfig.getInstance().getConfig("settings", "verbose").equals("true")) {
            System.out.println(preparedStatement);
        }

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            resultado = new Clube((int) resultSet.getObject(1));
            resultado.setNome((String) resultSet.getObject(2));
            resultado.setSigla((String) resultSet.getObject(3));
            resultado.setCnpj((String) resultSet.getObject(4));
            resultado.setEstadio((String) resultSet.getObject(5));
            resultado.setOrcamento(Double.parseDouble(resultSet.getObject(6).toString()));
        }

        resultSet.close();
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        return resultado;
    }

    @Override
    public ArrayList<Clube> getAll() throws Exception {
        ArrayList<Clube> resultado = new ArrayList<>();
        String dql = "SELECT * FROM clube";
        Connection connection = DataBaseConnections.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(dql);

        while (resultSet.next()) {
            Clube clube = new Clube((int) resultSet.getObject(1));
            clube.setNome((String) resultSet.getObject(2));
            clube.setSigla((String) resultSet.getObject(3));
            clube.setCnpj((String) resultSet.getObject(4));
            clube.setEstadio((String) resultSet.getObject(5));
            clube.setOrcamento(Double.parseDouble(resultSet.getObject(6).toString()));
            resultado.add(clube);
        }

        resultSet.close();
        statement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        return resultado;
    }
}