package by.epam.javatr.minchuk.project.controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface {@code Command}
 *
 * @author Oksana Minchuk
 * @version 1.0 09/05/2019
 */

public interface Command {

    String execute(HttpServletRequest request);
}
