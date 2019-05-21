package by.epam.javatr.minchuk.project.controller.command;

import by.epam.javatr.minchuk.project.controller.Command;
import by.epam.javatr.minchuk.project.controller.PageContainer;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Class {@code ToLoginPageCommand}
 *
 * @author Oksana Minchuk
 * @version 1.0 10/05/2019
 */

public class ToLoginPageCommand implements Command {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    public static final String TO_LOGIN = "toLogin";

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.debug("start ToLoginPageCommand");

        String toLogin = request.getParameter(TO_LOGIN);
        request.setAttribute(TO_LOGIN, toLogin);

        LOGGER.debug("finish ToLoginPageCommand");
        return PageContainer.LOGIN_PAGE;
    }
}
