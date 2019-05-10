package by.epam.javatr.minchuk.project.controller;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class {@code Controller}
 *
 * @author Oksana Minchuk
 * @version 1.0 06/05/2019
 */

public class Controller extends HttpServlet {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    public Controller() {
    }

    public void init() {
        LOGGER.debug("servlet init");
    }

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("start get method");
        response.setContentType("text/html");
        response.getWriter().print("This is " + this.getClass().getName() + ", using the GET method");
    }

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("start post methodd");
        response.setContentType("text/html");
        response.getWriter().print("This is " + this.getClass().getName() + ", using the Post method");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

    }

    public void destroy() {
        LOGGER.debug("servlet destroy");
        super.destroy();
    }

}
