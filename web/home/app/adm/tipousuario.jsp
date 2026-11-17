<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="model.TipoUsuario" %>
<%@page import="model.TipoUsuarioDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tipo Usuários</title>
    </head>
    <body>
        
        <% 
            ArrayList<TipoUsuario> lista = new TipoUsuarioDAO().getAll();
        %>
        
        <%@include file="/home/app/modulos.jsp"%>
        
        <h1>Tipo Usuários</h1>
        
        <table>
            
            <tr>
                <th>Id</th>
                <th>Módulo Administrativo</th>
                <th>Módulo Elenco</th>
                <th>Módulo Financeiro</th>
                <th></th>
                <th></th>
            </tr>
            
            <% for( TipoUsuario usTp : lista ) { %>
                <tr>
                    
                    <td><%= usTp.getId() %></td>
                    <td><%= usTp.getModuloAdministrativo()%></td>
                    <td><%= usTp.getModuloElenco()%></td>
                    <td><%= usTp.getModuloFinanceiro()%></td>
                    
                    <td><a href="<%= request.getContextPath() %>/home/app/adm/tipousuario_form.jsp?id=<%= usTp.getId() %>">Alterar</a></td>
                    
                    <td><a href="<%= request.getContextPath() %>/home?task=tipousuario&action=delete&id=<%= usTp.getId()%>" onclick="return confirm('Deseja realmente excluir Tipo Usuário <%= usTp.getId() %>')" >Excluir</a></td>
                    
                </tr>
            <% } %>
        
        </table>
            
        <button onclick="window.location.href='<%= request.getContextPath() %>/home/app/adm/tipousuario_form.jsp'">Adicionar</button>
        
    </body>
</html>