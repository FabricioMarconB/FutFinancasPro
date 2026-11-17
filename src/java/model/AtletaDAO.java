package model;

import framework.config.AppConfig;
import framework.dao.DataAccessObject;
import framework.dao.DataBaseConnections;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AtletaDAO extends DataAccessObject<Atleta> {

    @Override
    public void insert(Atleta t) throws Exception {
        Connection connection = DataBaseConnections.getInstance().getConnection();
        String dml = "INSERT INTO atleta (id, nome, cpf, data_nascimento, posicao, numero_camisa) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(dml);

        preparedStatement.setInt(1, t.getId());
        preparedStatement.setString(2, t.getNome());
        preparedStatement.setString(3, t.getCpf());
        preparedStatement.setString(4, t.getDataNascimento());
        preparedStatement.setString(5, t.getPosicao());
        preparedStatement.setInt(6, t.getNumeroCamisa());

        if (AppConfig.getInstance().getConfig("settings", "verbose").equals("true")) {
            System.out.println(preparedStatement);
        }

        preparedStatement.execute();
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
    }

    @Override
    public void update(Atleta t) throws Exception {
        Connection connection = DataBaseConnections.getInstance().getConnection();
        String dml = "UPDATE atleta SET nome = ?, cpf = ?, data_nascimento = ?, posicao = ?, numero_camisa = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(dml);

        preparedStatement.setString(1, t.getNome());
        preparedStatement.setString(2, t.getCpf());
        preparedStatement.setString(3, t.getDataNascimento());
        preparedStatement.setString(4, t.getPosicao());
        preparedStatement.setInt(5, t.getNumeroCamisa());
        preparedStatement.setInt(6, t.getId());

        if (AppConfig.getInstance().getConfig("settings", "verbose").equals("true")) {
            System.out.println(preparedStatement);
        }

        preparedStatement.execute();
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
    }

    @Override
    public void delete(Atleta t) throws Exception {
        Connection connection = DataBaseConnections.getInstance().getConnection();
        String dml = "DELETE FROM atleta WHERE id = ?";
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
    public Atleta getUnique(Object... values) throws Exception {
        Atleta resultado = null;
        String dql = "SELECT * FROM atleta WHERE id = ?";
        Connection connection = DataBaseConnections.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(dql);

        preparedStatement.setInt(1, (int) values[0]);

        if (AppConfig.getInstance().getConfig("settings", "verbose").equals("true")) {
            System.out.println(preparedStatement);
        }

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            resultado = new Atleta((int) resultSet.getObject(1));
            resultado.setNome((String) resultSet.getObject(2));
            resultado.setCpf((String) resultSet.getObject(3));
            if (resultSet.getObject(4) != null) {
                resultado.setDataNascimento(resultSet.getObject(4).toString());
            }
            resultado.setPosicao((String) resultSet.getObject(5));
            resultado.setNumeroCamisa((int) resultSet.getObject(6));
        }

        resultSet.close();
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        return resultado;
    }

    @Override
    public ArrayList<Atleta> getAll() throws Exception {
        ArrayList<Atleta> resultado = new ArrayList<>();
        String dql = "SELECT * FROM atleta";
        Connection connection = DataBaseConnections.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(dql);

        while (resultSet.next()) {
            Atleta atleta = new Atleta((int) resultSet.getObject(1));
            atleta.setNome((String) resultSet.getObject(2));
            atleta.setCpf((String) resultSet.getObject(3));
            if (resultSet.getObject(4) != null) {
                atleta.setDataNascimento(resultSet.getObject(4).toString());
            }
            atleta.setPosicao((String) resultSet.getObject(5));
            atleta.setNumeroCamisa((int) resultSet.getObject(6));
            resultado.add(atleta);
        }

        resultSet.close();
        statement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        return resultado;
    }
}