<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Clube" %>
<%@page import="model.ClubeDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Clube</title>
    </head>
    <body>
        
        <%
            String action = "new";
            Clube cl = null;
            String id = request.getParameter("id");
            if( id != null ) {
                cl = new ClubeDAO().getUnique( Integer.parseInt( id ) );
                if( cl != null ) action = "update";
            }
        %>
        
        <%@include file="/home/app/modulos.jsp"%>
        
        <h1>Cadastro Clube</h1>
        
        <form action="<%= request.getContextPath() %>/home?task=clube&action=<%= action %>" method="post">
            
            <label for="id">Id:</label>
            <input type="text" pattern="[0-9]*" id="id" name="id" value="<%= cl != null ? cl.getId() : "" %>" required <%= cl != null ? "readonly" : "" %>> <br/>
            
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" value="<%= cl != null ? cl.getNome() : "" %>" required><br/>
            
            <label for="sigla">Sigla (3 letras maiúsculas):</label>
            <input type="text" pattern="^[A-Z]{3}$" title="Exatamente 3 letras maiúsculas (Ex: SEP, FLA, COR)" id="sigla" name="sigla" value="<%= cl != null ? cl.getSigla() : "" %>" required><br/>
            
            <label for="cnpj">CNPJ (00.000.000/0000-00):</label>
            <input type="text" pattern="^\d{2}\.\d{3}\.\d{3}/\d{4}-\d{2}$" title="Formato: 00.000.000/0000-00" id="cnpj" name="cnpj" value="<%= cl != null ? cl.getCnpj() : "" %>" required><br/>
            
            <label for="estadio">Estádio Sede:</label>
            <input type="text" id="estadio" name="estadio" value="<%= cl != null ? cl.getEstadio() : "" %>" required><br/>
            
            <label for="orcamento">Orçamento Anual (R$):</label>
            <input type="number" step="0.01" id="orcamento" name="orcamento" value="<%= cl != null ? cl.getOrcamento() : "" %>" required><br/>
            
            <input type="submit" value="Salvar">
            
        </form>
        
    </body>
</html>