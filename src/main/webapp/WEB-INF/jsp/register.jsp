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

   <h5>REGISTER PAGE</h5>

   </body>
</html>