<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.TipoUsuario" %>
<%@page import="model.TipoUsuarioDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Tipo Usuário</title>
    </head>
    <body>
        
        <%
            String action = "new";
            TipoUsuario tpUs = null;
            String id = request.getParameter("id");
            if( id != null ) {
                tpUs = new TipoUsuarioDAO().getUnique( Integer.parseInt( id ) );
                
                if( tpUs != null ) action = "update";
            }
        %>
        
        <%@include file="/home/app/modulos.jsp"%>
        
        <h1>Cadastro Tipo Usuário</h1>
        
        <form action="<%= request.getContextPath() %>/home?task=tipousuario&action=<%= action %>" method="post">
            
            <label for="id">Id:</label>
            <input type="text" pattern="[0-9]*" id="id" name="id" value="<%= tpUs != null ? tpUs.getId() : "" %>" required <%= tpUs != null ? "readonly" : "" %>> <br/>
            
            <input type="checkbox" id="modulo_administrativo" name="modulo_administrativo" value="S" <%= ( (tpUs != null) && ( tpUs.getModuloAdministrativo().equals("S") ) ) ? "checked" : "" %> >
            <label for="modulo_administrativo">Módulo Administrativo</label> <br> <br>
            
            <input type="checkbox" id="modulo_elenco" name="modulo_elenco" value="S" <%= ( (tpUs != null) && ( tpUs.getModuloElenco().equals("S") ) ) ? "checked" : "" %> >
            <label for="modulo_elenco">Módulo Elenco (Contratos)</label> <br> <br>
            
            <input type="checkbox" id="modulo_financeiro" name="modulo_financeiro" value="S" <%= ( (tpUs != null) && ( tpUs.getModuloFinanceiro().equals("S") ) ) ? "checked" : "" %> >
            <label for="modulo_financeiro">Módulo Financeiro</label> <br> <br>
            
            <input type="submit" value="Salvar">
            
        </form>
        
    </body>
</html>