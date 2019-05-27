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
            <fmt:message key="local.country" var="country"/>
            <fmt:message key="local.dateFrom" var="dateFrom"/>
            <fmt:message key="local.dateTo" var="dateTo"/>
            <fmt:message key="local.tour.type" var="tourtype"/>
            <fmt:message key="local.tour.hot" var="tourhot"/>
            <fmt:message key="local.tour.price" var="tourprice"/>
            <fmt:message key="local.hotel.name" var="hotelname"/>
            <fmt:message key="local.transport" var="transport"/>
            <fmt:message key="local.addVaucherTable" var="addVaucherTable"/>
            <fmt:message key="local.Greece" var="Greece"/>
            <fmt:message key="local.Poland" var="Poland"/>
            <fmt:message key="local.Spain" var="Spain"/>
            <fmt:message key="local.Montenegro" var="Montenegro"/>
            <fmt:message key="local.Russia" var="Russia"/>
            <fmt:message key="local.Bulgaria" var="Bulgaria"/>
            <fmt:message key="local.beach" var="beach"/>
            <fmt:message key="local.shopping" var="shopping"/>
            <fmt:message key="local.excursion" var="excursion"/>
            <fmt:message key="local.fitness" var="fitness"/>
            <fmt:message key="local.weekend" var="weekend"/>
            <fmt:message key="local.countryName" var="countryName"/>
            <fmt:message key="local.send" var="send"/>
            <fmt:message key="local.plain" var="plain"/>
            <fmt:message key="local.train" var="train"/>
            <fmt:message key="local.bus" var="bus"/>
            <fmt:message key="local.auto" var="auto"/>
            <fmt:message key="local.updateTourTable" var="updateTourTable"/>
        </fmt:bundle>
   </head>

   <body>
        <form  action="Controller" method="GET"  align="center" style="margin: 15px">
            <input type="hidden" name="command" value="logout" />
            <input type="submit" class="btn btn-warning" value="${logOut}"/>
        </form>


<jsp:useBean id="tourService" class="by.epam.javatr.minchuk.project.service.impl.TourServiceImpl" scope="application"/>
<jsp:useBean id="hotelService" class="by.epam.javatr.minchuk.project.service.impl.HotelServiceImpl" scope="application"/>
<jsp:useBean id="vaucherService" class="by.epam.javatr.minchuk.project.service.impl.VaucherServiceImpl" scope="application"/>


<!------- ADD VAUCHER TABLE ------------->
<div align="center" style="margin-top: 5px; margin-bottom: 5px">
<form action="Controller" method="post">
<input type="hidden" name="command" value="add_vaucher" />
<table>
<caption style="color: GreenYellow; font-weight: bold">${addVaucherTable}</caption>
    <tr>
        <td>
            <table border="1"  style="border: 3px ridge DarkBlue">
            <tr align="center" style="font-weight: bold">
               <td>${country}</td>
               <td>${dateFrom}</td>
               <td>${dateTo}</td>
               <td>${tourtype}</td>
               <td>${transport}</td>
               <td>${hotelname}</td>
           </tr>
           <tr>
                   <td>
                        <select name="countryVal">
                             <option selected disabled>${countryName}</option>
                             <option value="Греция">${Greece}</option>
                             <option value="Польша">${Poland}</option>
                             <option value="Испания">${Spain}</option>
                             <option value="Черногория">${Montenegro}</option>
                             <option value="Россия">${Russia}</option>
                             <option value="Болгария">${Bulgaria}</option>
                        </select>
                   </td>
                   <td>
                        <input type="text" path="dateFromVal" name="dateFromVal" class="form-control" required="required"
                                placeholder="YYYY-MM-DD" title="${dateFrom}"/>
                   </td>
                   <td>
                        <input type="text" path="dateToVal" name="dateToVal" class="form-control" required="required"
                                placeholder="YYYY-MM-DD" title="${dateTo}"/>
                   </td>
                   <td>
                       <select name="idTour">
                            <option selected disabled>${tourtype}</option>
                            <c:forEach var="tourVal" items="${tourService.findAll()}">
                                <option value="<c:out value="${tourVal.id}"/>" > ${tourVal.type}/${tourVal.price}/hot:${tourVal.hot}</option>
                            </c:forEach>
                       </select>
                   </td>
                   <td>
                       <select name="idTransport">
                           <option selected disabled>${transport}</option>
                           <option value="1">${plain}</option>
                           <option value="2">${train}</option>
                           <option value="3">${bus}</option>
                           <option value="4">${auto}</option>
                       </select>
                   </td>
                   <td>
                       <select name="idHotel">
                           <option selected disabled>${hotelname}</option>
                           <c:forEach var="hotelVal" items="${hotelService.findAll()}">
                                <option value="<c:out value="${hotelVal.id}"/>" > ${hotelVal.name}/${hotelVal.pricePerDay}</option>
                           </c:forEach>
                       </select>
                   </td>
           </tr>
           </table>
           <p>${acceptedMessageAdminAdd}</p>
       </td>
    </tr>
</table>

    <div align="middle" style="margin-top: 10px">
        <input type="submit" class="btn btn-success" value="${send}"/>

    </div>
</form>
</div>
<hr align="center" width="90%" size="10" color="GreenYellow" />


<!------- UPDATE TOUR TABLE ------------->
<div align="center" style="margin-top: 5px; margin-bottom: 5px">
<form action="Controller" method="post">
<input type="hidden" name="command" value="update_tour" />
<input type="hidden" name="idTour" value="${sessionScope.id}"/>
<table>
<caption style="color: GreenYellow; font-weight: bold">${updateTourTable}</caption>
    <tr>
        <td>
            <table border="1"  style="border: 3px ridge DarkBlue">
            <tr align="center" style="font-weight: bold">
               <td>ID</td>
               <td>${tourtype}</td>
               <td>${tourprice}</td>
               <td>${tourhot}</td>
               <td></td>
           </tr>
           <c:forEach var="tour" items="${tourService.findAll()}" varStatus="status">
               <tr>
                   <td><c:out value="${tour.id}"/></td>
                   <td>${tour.type}</td>
                   <td>${tour.price}</td>
                   <td>
                   <select name="isHot">
                        <option selected>${tour.hot}</option>
                        <option value="true">true</option>
                        <option value="false">false</option>
                   </select>
                   </td>
                   <td>
                        <input type="submit" class="btn btn-success" value="${send}"/>
                   </td>
               </tr>
           </c:forEach>
           </table>
           <p>${acceptedMessageAdminUpdate}</p>
       </td>
    </tr>
</table>



</form>
</div>
<hr align="center" width="90%" size="10" color="GreenYellow" />

   <%@ include file="footer.jsp" %>
   </body>
</html>