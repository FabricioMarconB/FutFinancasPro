package model;

import framework.config.AppConfig;
import framework.dao.DataAccessObject;
import framework.dao.DataBaseConnections;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class LancamentoFinanceiroDAO extends DataAccessObject<LancamentoFinanceiro> {

    @Override
    public void insert(LancamentoFinanceiro t) throws Exception {
        Connection connection = DataBaseConnections.getInstance().getConnection();
        String dml = "INSERT INTO lancamento_financeiro (id, clube_id, descricao, tipo, categoria, valor, data_competencia) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(dml);

        preparedStatement.setInt(1, t.getId());
        preparedStatement.setInt(2, t.getClubeId());
        preparedStatement.setString(3, t.getDescricao());
        preparedStatement.setString(4, t.getTipo());
        preparedStatement.setString(5, t.getCategoria());
        preparedStatement.setDouble(6, t.getValor());
        preparedStatement.setString(7, t.getDataCompetencia());

        if (AppConfig.getInstance().getConfig("settings", "verbose").equals("true")) {
            System.out.println(preparedStatement);
        }

        preparedStatement.execute();
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
    }

    @Override
    public void update(LancamentoFinanceiro t) throws Exception {
        Connection connection = DataBaseConnections.getInstance().getConnection();
        String dml = "UPDATE lancamento_financeiro SET clube_id = ?, descricao = ?, tipo = ?, categoria = ?, valor = ?, data_competencia = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(dml);

        preparedStatement.setInt(1, t.getClubeId());
        preparedStatement.setString(2, t.getDescricao());
        preparedStatement.setString(3, t.getTipo());
        preparedStatement.setString(4, t.getCategoria());
        preparedStatement.setDouble(5, t.getValor());
        preparedStatement.setString(6, t.getDataCompetencia());
        preparedStatement.setInt(7, t.getId());

        if (AppConfig.getInstance().getConfig("settings", "verbose").equals("true")) {
            System.out.println(preparedStatement);
        }

        preparedStatement.execute();
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
    }

    @Override
    public void delete(LancamentoFinanceiro t) throws Exception {
        Connection connection = DataBaseConnections.getInstance().getConnection();
        String dml = "DELETE FROM lancamento_financeiro WHERE id = ?";
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
    public LancamentoFinanceiro getUnique(Object... values) throws Exception {
        LancamentoFinanceiro resultado = null;
        String dql = "SELECT * FROM lancamento_financeiro WHERE id = ?";
        Connection connection = DataBaseConnections.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(dql);

        preparedStatement.setInt(1, (int) values[0]);

        if (AppConfig.getInstance().getConfig("settings", "verbose").equals("true")) {
            System.out.println(preparedStatement);
        }

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            resultado = new LancamentoFinanceiro((int) resultSet.getObject(1));
            resultado.setClubeId((int) resultSet.getObject(2));
            resultado.setDescricao((String) resultSet.getObject(3));
            resultado.setTipo((String) resultSet.getObject(4));
            resultado.setCategoria((String) resultSet.getObject(5));
            resultado.setValor(Double.parseDouble(resultSet.getObject(6).toString()));
            if (resultSet.getObject(7) != null) resultado.setDataCompetencia(resultSet.getObject(7).toString());
        }

        resultSet.close();
        preparedStatement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        return resultado;
    }

    @Override
    public ArrayList<LancamentoFinanceiro> getAll() throws Exception {
        ArrayList<LancamentoFinanceiro> resultado = new ArrayList<>();
        String dql = "SELECT * FROM lancamento_financeiro";
        Connection connection = DataBaseConnections.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(dql);

        while (resultSet.next()) {
            LancamentoFinanceiro lf = new LancamentoFinanceiro((int) resultSet.getObject(1));
            lf.setClubeId((int) resultSet.getObject(2));
            lf.setDescricao((String) resultSet.getObject(3));
            lf.setTipo((String) resultSet.getObject(4));
            lf.setCategoria((String) resultSet.getObject(5));
            lf.setValor(Double.parseDouble(resultSet.getObject(6).toString()));
            if (resultSet.getObject(7) != null) lf.setDataCompetencia(resultSet.getObject(7).toString());
            resultado.add(lf);
        }

        resultSet.close();
        statement.close();
        DataBaseConnections.getInstance().closeConnection(connection);
        return resultado;
    }
}