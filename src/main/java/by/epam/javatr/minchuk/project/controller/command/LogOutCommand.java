package by.epam.javatr.minchuk.project.controller.command;

import by.epam.javatr.minchuk.project.controller.Command;
import by.epam.javatr.minchuk.project.controller.PageContainer;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Class {@code LogOutCommand}
 *
 * @author Oksana Minchuk
 * @version 1.0 10/05/2019
 */

public class LogOutCommand implements Command {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.debug("start LogOutCommand");

        request.getSession().invalidate();

        LOGGER.debug("finish LogOutCommand");
        return PageContainer.INDEX_PAGE;
    }
}
