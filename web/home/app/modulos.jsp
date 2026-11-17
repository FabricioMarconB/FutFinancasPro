<%@page import="model.Usuario"%>
<%@page import="model.TipoUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilo.css">
<%
    String nomeUsuarioSessao = "";
    if( ( session != null ) && ( session.getAttribute("usuario_sessao") != null ) ) {
        Usuario usuarioSessao = (Usuario) session.getAttribute("usuario_sessao");
        nomeUsuarioSessao = usuarioSessao.getId() + " " + usuarioSessao.getNome();
    }
    
    TipoUsuario tipoUsuarioSessao = null;
    if( ( session != null ) && ( session.getAttribute("tipo_usuario_sessao") != null ) ) {
        tipoUsuarioSessao = (TipoUsuario) session.getAttribute("tipo_usuario_sessao");
    }
%>
<h1>Menu - FutFinanças Pro</h1>
<menu>
    
    <li><a href="<%= request.getContextPath() %>/home/app/menu.jsp">Home</a></li>
    
    <% if( ( tipoUsuarioSessao != null ) &&
            ( tipoUsuarioSessao.getModuloAdministrativo().equals("S") == true ) ) { %>
            
            <li><a href="<%= request.getContextPath() %>/home/app/adm/tipousuario.jsp">Tipos Usuário</a></li>
            <li><a href="<%= request.getContextPath() %>/home/app/adm/usuario.jsp">Usuários</a></li>
            <li><a href="<%= request.getContextPath() %>/home/app/adm/clube.jsp">Clubes (CRUD 1)</a></li>
            <li><a href="<%= request.getContextPath() %>/home/app/adm/atleta.jsp">Atletas (CRUD 2)</a></li>
            
    <% } %>

    <% if( ( tipoUsuarioSessao != null ) &&
            ( tipoUsuarioSessao.getModuloElenco().equals("S") == true ) ) { %>
            
            <li><a href="<%= request.getContextPath() %>/home/app/elenco/contrato.jsp">Contratos de Elenco (CRUD 3)</a></li>
            
    <% } %>

    <% if( ( tipoUsuarioSessao != null ) &&
            ( tipoUsuarioSessao.getModuloFinanceiro().equals("S") == true ) ) { %>
            
            <li><a href="<%= request.getContextPath() %>/home/app/financeiro/lancamento.jsp">Lançamentos Financeiros (CRUD 4)</a></li>
            
    <% } %>
    
    <li><a href="<%= request.getContextPath() %>/home?task=logout"><%= nomeUsuarioSessao %> -- Logout</a></li>
    
</menu>