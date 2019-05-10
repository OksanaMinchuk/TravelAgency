<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
   <head>
       <title>GSP TIMING</title>
   </head>

   <body>

   <h5>Счетчик времени от запуска приложения до нажатия кнопки111</h5>

   <jsp:useBean id="calendar" class="java.util.GregorianCalendar"/>

   <form name="Simple" action="Controller" method="POST">
       <input type="hidden" name="time" value="${calendar.timeInMillis}"/>
       <input type="submit" name="button" value="Посчитать время"/>
   </form>

   <form action = "Controller" method="get">
        <input type="submit" name="time" value="Execute"/>
   </form>
   </body>
</html>