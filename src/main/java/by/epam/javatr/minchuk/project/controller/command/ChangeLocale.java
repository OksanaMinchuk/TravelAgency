package by.epam.javatr.minchuk.project.controller.command;

import by.epam.javatr.minchuk.project.controller.Command;
import by.epam.javatr.minchuk.project.controller.PageContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;

/**
 * Class {@code ChangeLocale}
 *
 * @author Oksana Minchuk
 * @version 1.0 18/05/2019
 */

public class ChangeLocale implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        String newLocalization = request.getParameter("localization");

        request.getSession().setAttribute("localization", newLocalization);
        Config.set(request.getSession(), Config.FMT_LOCALE, newLocalization);

        return PageContainer.INDEX_PAGE;
    }
}
