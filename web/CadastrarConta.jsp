<%-- 
    Document   : CadastrarConta
    Created on : 10/10/2018, 20:35:47
    Author     : Lucas PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Conta asd</title>
    </head>
    <body>
        <h1>Cadastro de Conta </h1>
        <form action="CadastrarConta" method="post">
            <p>Numero da conta: <input type="text" name="nconta" required="required"></p>
            <p>CPF: <input type="text" name="cpf" required="required"></p>
            <p><input type="submit" name="enviar" value="Enviar"></p>
        
        
        </form>
        
    </body>
</html>
