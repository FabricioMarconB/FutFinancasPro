<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aviso do Sistema - FutFinanças Pro</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilo.css">
    </head>
    <body>
        <h1>Aviso do Sistema</h1>
        <p>Não foi possível concluir a operação solicitada.</p>
        <p><strong>Mensagem:</strong> <%= (exception != null) ? exception.getMessage() : "Página ou recurso não encontrado." %></p>
        <p><small>O registro técnico foi gravado automaticamente no arquivo XML de monitoramento (ExceptionLogTrack).</small></p>
        <button onclick="window.location.href='<%= request.getContextPath() %>/home/app/menu.jsp'">Voltar ao Menu</button>
    </body>
</html>