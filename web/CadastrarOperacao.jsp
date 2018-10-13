<%-- 
    Document   : CadastrarOperacao
    Created on : 13/10/2018, 12:54:02
    Author     : Lucas PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Operação</title>
    </head>
    <body>
        
        <h1>Operação</h1>
        <form action="CadastrarOperacao" method="post">
            <p>Numero da conta: <input type="text" name="nconta" required="required"></p>
            <p>Valor: <input type="text" name="valor" required="required"></p>
            <p><input type="submit" name="enviar" value="Enviar"></p>
        </form>
            
            
        
    </body>
</html>
