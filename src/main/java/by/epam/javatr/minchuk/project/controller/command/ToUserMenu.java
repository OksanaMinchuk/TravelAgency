package by.epam.javatr.minchuk.project.controller.command;

import by.epam.javatr.minchuk.project.controller.Command;
import by.epam.javatr.minchuk.project.controller.PageContainer;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ToUserMenu implements Command {
    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    public static final String TO_USER_MENU = "toUserMenu";

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.debug("start ToUserMenu");

        String toUserMenu = request.getParameter(TO_USER_MENU);
        request.setAttribute(TO_USER_MENU, toUserMenu);

        LOGGER.debug("finish ToUserMenu");
        return PageContainer.USER_MENU_PAGE;
    }
}
