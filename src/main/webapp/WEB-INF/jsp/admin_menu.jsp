<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
   <head>
   <title>Admin menu</title>
        <%@include file="header.jsp" %>
        <%@ include file="include.jsp" %>
        <style type="text/css"><%@include file="/resources/css/style.css"%></style>

        <fmt:setLocale value="${sessionScope.localization}"/>
        <fmt:setBundle basename="localization.local" var="local"/>

        <fmt:bundle basename="localization">
            <fmt:message key="local.logOut" var="logOut"/>
        </fmt:bundle>

   </head>

   <body>
        <form action="Controller" method="GET"  align="center">
            <input type="hidden" name="command" value="logout" />
            <input type="submit" class="btn btn-success" value="${logOut}"/>
        </form>


   <%@ include file="footer.jsp" %>
   </body>
</html>