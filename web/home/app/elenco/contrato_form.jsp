<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Contrato" %>
<%@page import="model.ContratoDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Contrato</title>
    </head>
    <body>
        
        <%
            String action = "new";
            Contrato ct = null;
            String id = request.getParameter("id");
            if( id != null ) {
                ct = new ContratoDAO().getUnique( Integer.parseInt( id ) );
                if( ct != null ) action = "update";
            }
        %>
        
        <%@include file="/home/app/modulos.jsp"%>
        
        <h1>Cadastro Contrato</h1>
        
        <form action="<%= request.getContextPath() %>/home?task=contrato&action=<%= action %>" method="post">
            
            <label for="id">Id:</label>
            <input type="text" pattern="[0-9]*" id="id" name="id" value="<%= ct != null ? ct.getId() : "" %>" required <%= ct != null ? "readonly" : "" %>> <br/>
            
            <label for="clube_id">Clube Id:</label>
            <input type="text" pattern="[0-9]*" id="clube_id" name="clube_id" value="<%= ct != null ? ct.getClubeId() : "" %>" required><br/>
            
            <label for="atleta_id">Atleta Id:</label>
            <input type="text" pattern="[0-9]*" id="atleta_id" name="atleta_id" value="<%= ct != null ? ct.getAtletaId() : "" %>" required><br/>
            
            <label for="salario">Salário Mensal (R$):</label>
            <input type="number" step="0.01" id="salario" name="salario" value="<%= ct != null ? ct.getSalario() : "" %>" required><br/>
            
            <label for="multa_rescisoria">Multa Rescisória (R$):</label>
            <input type="number" step="0.01" id="multa_rescisoria" name="multa_rescisoria" value="<%= ct != null ? ct.getMultaRescisoria() : "" %>" required><br/>
            
            <label for="data_inicio">Data de Início:</label>
            <input type="date" id="data_inicio" name="data_inicio" value="<%= ( ct != null && ct.getDataInicio() != null ) ? ct.getDataInicio() : "" %>" required><br/>
            
            <label for="data_fim">Data de Fim:</label>
            <input type="date" id="data_fim" name="data_fim" value="<%= ( ct != null && ct.getDataFim() != null ) ? ct.getDataFim() : "" %>" required><br/>
            
            <label for="status">Status Contratual:</label>
            <input type="text" id="status" name="status" value="<%= ct != null ? ct.getStatus() : "ATIVO" %>" required><br/>
            
            <input type="submit" value="Salvar">
            
        </form>
        
    </body>
</html>