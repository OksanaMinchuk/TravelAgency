package by.epam.javatr.minchuk.project.controller.command;

import by.epam.javatr.minchuk.project.controller.Command;
import by.epam.javatr.minchuk.project.controller.PageContainer;

import javax.servlet.http.HttpServletRequest;

public class ToLoginPageCommand implements Command {

    public static final String TO_LOGIN = "toLogin";

    @Override
    public String execute(HttpServletRequest request) {
        String toLogin = request.getParameter(TO_LOGIN);
        request.setAttribute(TO_LOGIN, toLogin);
        return PageContainer.LOGIN_PAGE;
    }
}
