package by.epam.javatr.minchuk.project.controller.command;

import by.epam.javatr.minchuk.project.controller.Command;
import by.epam.javatr.minchuk.project.controller.PageContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * Class {@code EmptyCommand} the command will be executed if the servlet is accessed without a command
 *
 * @author Oksana Minchuk
 * @version 1.0 10/05/2019
 */

public class EmptyCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return PageContainer.ERROR_PAGE;
    }
}
