package by.epam.javatr.minchuk.project.controller.command;

import by.epam.javatr.minchuk.project.controller.Command;
import by.epam.javatr.minchuk.project.controller.PageContainer;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Class {@code ToRegisterPageCommand}
 *
 * @author Oksana Minchuk
 * @version 1.0 10/05/2019
 */

public class ToRegisterPageCommand implements Command {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    public static final String TO_REGISTR = "toRegister";

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.debug("start ToRegisterPageCommand");

        String toRegister = request.getParameter(TO_REGISTR);
        request.setAttribute(TO_REGISTR, toRegister);

        LOGGER.debug("finish ToRegisterPageCommand");
        return PageContainer.REGISTER_PAGE;
    }
}
