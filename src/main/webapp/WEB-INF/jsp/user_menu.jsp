<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
   <head>
   <title>User menu</title>
        <%@include file="header.jsp" %>
        <%@ include file="include.jsp" %>
        <style type="text/css"><%@include file="/resources/css/style.css"%></style>

        <fmt:setLocale value="${sessionScope.localization}"/>
        <fmt:setBundle basename="localization.local" var="local"/>

        <fmt:bundle basename="localization">
           <fmt:message key="local.logOut" var="logOut"/>
           <fmt:message key="local.viewAllVaucher" var="viewAllVaucher"/>
           <fmt:message key="local.country" var="country"/>
           <fmt:message key="local.dateFrom" var="dateFrom"/>
           <fmt:message key="local.dateTo" var="dateTo"/>
           <fmt:message key="local.tour.type" var="tourtype"/>
           <fmt:message key="local.tour.price" var="tourprice"/>
           <fmt:message key="local.tour.hot" var="tourhot"/>
           <fmt:message key="local.hotel.name" var="hotelname"/>
           <fmt:message key="local.hotel.pricePerDay" var="hotelpricePerDay"/>
           <fmt:message key="local.transport" var="transport"/>
           <fmt:message key="local.chooseVaucher" var="chooseVaucher"/>
           <fmt:message key="local.priceColumn" var="priceColumn"/>
           <fmt:message key="local.book" var="book"/>
        </fmt:bundle>

   </head>

   <body>
    <form action="Controller" method="GET"  align="center" style="margin: 15px">
         <input type="hidden" name="command" value="logout" />
         <input type="submit" class="btn btn-success" value="${logOut}"/>
    </form>


        <div align="center">
            <table width=800px border="1"  style="border: 3px ridge DarkBlue">
                <tr>
                    <td colspan="2">
                        <p align="center" style="color: DarkBlue; font-weight: bold; font-size: 16px; font-style: italic">
                                <a href="Controller?command=choose_vaucher" class="button">${chooseVaucher}</a>
                        </p>
                    </td>
                </tr>
            </table>
         </div>

<div align="center" style="margin-top: 5px; margin-bottom: 5px">
<table>
<tr>
        <td>
            <table border="1"  style="border: 3px ridge DarkBlue">
            <tr align="center" style="font-weight: bold">
               <td>№</td>
               <td>${country}</td>
               <td>${dateFrom}</td>
               <td>${dateTo}</td>
               <td>${tourtype}</td>
               <td>${tourprice}</td>
               <td>${tourhot}</td>
               <td>${hotelname}</td>
               <td>${hotelpricePerDay}</td>
               <td>${transport}</td>
           </tr>
           <c:forEach var="vaucher" items="${vauchers}" varStatus="status">
               <tr>
                   <td><c:out value="${status.count}"/></td>
                   <td><c:out value="${vaucher.country}"/></td>
                   <td><c:out value="${vaucher.dateFrom}"/></td>
                   <td><c:out value="${vaucher.dateTo}"/></td>
                   <td><c:out value="${vaucher.tour.type}"/></td>
                   <td><c:out value="${vaucher.tour.price}"/></td>
                   <td><c:out value="${vaucher.tour.hot}"/></td>
                   <td><c:out value="${vaucher.hotel.name}"/></td>
                   <td><c:out value="${vaucher.hotel.pricePerDay}"/></td>
                   <td><c:out value="${vaucher.transport}"/></td>
           </tr>
           </c:forEach>
           </table>
       </td>

       <td>
           <table border="1"  style="border: 3px ridge DarkBlue">
           <tr align="center" style="font-weight: bold;">
                <td>${priceColumn}</td>
           </tr>
           <c:forEach var="vaucherPrice" items="${vaucherPrice}">
           <tr>
                <td><c:out value="${vaucherPrice}"/></td>
            </tr>
            </c:forEach>
           </table>
       </td>
       <td>
           <table border="1"  style="border: 3px ridge DarkBlue">
           <tr align="center" style="font-weight: bold;">
                <td>${book}</td>
           </tr>
           <c:forEach var="vaucher" items="${vauchers}">
           <tr>
                <td><a href="Controller?command=book_vaucher&idvaucher=${vaucher.id}" >${book}</a></td>
            </tr>
            </c:forEach>
           </table>
       </td>
</tr>
</table>
</div>


<div style="margin-bottom: 400px">
    <h3 style="color: red; font-weight: bold">${notEnouthMoneyMessage}</h3>
</div>

   <%@ include file="footer.jsp" %>
   </body>
</html>