<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
   <head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <title>Home</title>
       <fmt:setLocale value="${sessionScope.locale}"/>

       <fmt:bundle basename="localization">
           <fmt:message key="locale.index.welcom" var="welcom"/>
           <fmt:message key="local.index.register" var="register"/>
           <fmt:message key="local.index.logIn" var="logIn"/>
           <fmt:message key="local.index.logOut" var="logOut"/>
       </fmt:bundle>

   </head>

   <body>

 <!--  <form action="Controller" method="GET">
       <input type="hidden" name="command" value="$register"/>
       <input type="submit" name="button" value="Посчитать время"/>
   </form>

   <form action = "Controller" method="post">
        <input type="submit" name="time" value="Execute"/>
   </form>
   -->

   	<div class="buttons">
   		<form method="GET" action="Controller">
   			<input type="hidden" name="command" value="to_login" />
   		    <button class="but" type="submit" href="login">${logIn}</button>
   		</form>

   		<form method="GET" action="Controller">
   			<input type="hidden" name="command" value="to_register" />
   			<input type="hidden" name="toRegistr" value="client" />
   		    <button class="but" type="submit">${register}</button>
   		</form>
   	</div>

   </body>
</html>