package by.epam.javatr.minchuk.project.controller.command;

import by.epam.javatr.minchuk.project.controller.Command;
import by.epam.javatr.minchuk.project.controller.PageContainer;

import javax.servlet.http.HttpServletRequest;

public class ToRegisterPageCommand implements Command {

    public static final String TO_REGISTR = "toRegister";

    @Override
    public String execute(HttpServletRequest request) {
        String toRegister = request.getParameter(TO_REGISTR);
        request.setAttribute(TO_REGISTR, toRegister);
        return PageContainer.REGISTER_PAGE;
    }
}
