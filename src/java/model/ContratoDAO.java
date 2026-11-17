package model;

import framework.config.AppConfig;
import framework.dao.DataAccessObject;
import framework.dao.DataBaseConnections;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ContratoDAO extends DataAccessObject<Contrato> {

    @Override
    public void insert(Contrato t) throws Exception {
        Connection connection = DataBaseConnections.getInstance().getConnection();
        String dml = "INSERT INTO contrato (id, clube_id, atleta_id, salario, multa_rescisoria, data_inicio, data_fim, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(dml);

        preparedStatement.setInt(1, t.getId());
        preparedStatement.setInt(2, t.getClubeId());
        preparedStatement.setInt(3, t.getAtletaId());
        preparedStatement.setDouble(4, t.getSalario());
        preparedStatement.setDouble(5, t.getMultaRescisoria());
        preparedStatement.setString(6, t.getDataInicio());
        preparedStatement.setString(7, t.getDataFim());
        preparedStatement.setString(8, t.getStatus());

        if (AppConfig.getInstance().getConfig("settings", "verbose").equals("true")) {
            System.out.println(preparedStatement);
        }

        preparedStatement.execute();
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
    }

    @Override
    public void update(Contrato t) throws Exception {
        Connection connection = DataBaseConnections.getInstance().getConnection();
        String dml = "UPDATE contrato SET clube_id = ?, atleta_id = ?, salario = ?, multa_rescisoria = ?, data_inicio = ?, data_fim = ?, status = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(dml);

        preparedStatement.setInt(1, t.getClubeId());
        preparedStatement.setInt(2, t.getAtletaId());
        preparedStatement.setDouble(3, t.getSalario());
        preparedStatement.setDouble(4, t.getMultaRescisoria());
        preparedStatement.setString(5, t.getDataInicio());
        preparedStatement.setString(6, t.getDataFim());
        preparedStatement.setString(7, t.getStatus());
        preparedStatement.setInt(8, t.getId());

        if (AppConfig.getInstance().getConfig("settings", "verbose").equals("true")) {
            System.out.println(preparedStatement);
        }

        preparedStatement.execute();
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
    }

    @Override
    public void delete(Contrato t) throws Exception {
        Connection connection = DataBaseConnections.getInstance().getConnection();
        String dml = "DELETE FROM contrato WHERE id = ?";
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
    public Contrato getUnique(Object... values) throws Exception {
        Contrato resultado = null;
        String dql = "SELECT * FROM contrato WHERE id = ?";
        Connection connection = DataBaseConnections.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(dql);

        preparedStatement.setInt(1, (int) values[0]);

        if (AppConfig.getInstance().getConfig("settings", "verbose").equals("true")) {
            System.out.println(preparedStatement);
        }

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            resultado = new Contrato((int) resultSet.getObject(1));
            resultado.setClubeId((int) resultSet.getObject(2));
            resultado.setAtletaId((int) resultSet.getObject(3));
            resultado.setSalario(Double.parseDouble(resultSet.getObject(4).toString()));
            resultado.setMultaRescisoria(Double.parseDouble(resultSet.getObject(5).toString()));
            if (resultSet.getObject(6) != null) resultado.setDataInicio(resultSet.getObject(6).toString());
            if (resultSet.getObject(7) != null) resultado.setDataFim(resultSet.getObject(7).toString());
            resultado.setStatus((String) resultSet.getObject(8));
        }

        resultSet.close();
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        return resultado;
    }

    @Override
    public ArrayList<Contrato> getAll() throws Exception {
        ArrayList<Contrato> resultado = new ArrayList<>();
        String dql = "SELECT * FROM contrato";
        Connection connection = DataBaseConnections.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(dql);

        while (resultSet.next()) {
            Contrato ct = new Contrato((int) resultSet.getObject(1));
            ct.setClubeId((int) resultSet.getObject(2));
            ct.setAtletaId((int) resultSet.getObject(3));
            ct.setSalario(Double.parseDouble(resultSet.getObject(4).toString()));
            ct.setMultaRescisoria(Double.parseDouble(resultSet.getObject(5).toString()));
            if (resultSet.getObject(6) != null) ct.setDataInicio(resultSet.getObject(6).toString());
            if (resultSet.getObject(7) != null) ct.setDataFim(resultSet.getObject(7).toString());
            ct.setStatus((String) resultSet.getObject(8));
            resultado.add(ct);
        }

        resultSet.close();
        statement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        return resultado;
    }
}