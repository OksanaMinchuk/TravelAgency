package by.epam.javatr.minchuk.project.controller.command;

import by.epam.javatr.minchuk.project.controller.Command;
import by.epam.javatr.minchuk.project.controller.PageContainer;
import by.epam.javatr.minchuk.project.model.entity.User;
import by.epam.javatr.minchuk.project.model.entity.type.RoleType;
import by.epam.javatr.minchuk.project.model.exception.logicexeption.TravelAgencyDataWrongException;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyDAOException;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyServiceException;
import by.epam.javatr.minchuk.project.service.ServiceFactory;
import by.epam.javatr.minchuk.project.service.UserService;
import by.epam.javatr.minchuk.project.service.validator.Validator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class {@code RegisterUserCommand}
 *
 * @author Oksana Minchuk
 * @version 1.0 10/05/2019
 */

public class RegisterUserCommand implements Command {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.debug("start RegisterUserCommand");
        String page;
        HttpSession session = request.getSession(true);
        if (validate(request)) {
            LOGGER.debug(request.getParameter("roleUser"));
            Validator validator = Validator.getInstance();

            try {
                if (validator.validateUniqeLogin(request.getParameter("login"))) {
                    User user = new User();
                    user.setName(request.getParameter("name"));
                    user.setSurname(request.getParameter("surname"));
                    user.setMoney(Double.parseDouble(request.getParameter("money")));
                    user.setEmail(request.getParameter("email"));
                    user.setLogin(request.getParameter("login"));
                    user.setPassword(request.getParameter("password"));
                    user.setRole(RoleType.valueOf(request.getParameter("userRole").toUpperCase()));

                    String role = request.getParameter("userRole");

                    ServiceFactory factory = ServiceFactory.getInstance();
                    UserService userService = factory.getUserService();

                        userService.create(user);
                        if (user != null) {
                            request.setAttribute("user", user);
                            session.setAttribute("user", user.getName());
                        }
                        if (role.equals("client")) {
                            page = PageContainer.USER_MENU_PAGE;
                        } else {
                            page = PageContainer.ADMIN_MENU_PAGE;
                        }
                } else {
                    session.setAttribute("error", "Login is not uniqe. Please try again.");
                    page = PageContainer.ERROR_PAGE;
                }
            } catch (TravelAgencyServiceException e) {
                LOGGER.error("RegisterUserCommand error.", e);
                page = PageContainer.ERROR_PAGE;
            } catch (TravelAgencyDataWrongException e) {
                LOGGER.error("RegisterUserCommand error.", e);
                page = PageContainer.ERROR_PAGE;
            } catch (TravelAgencyDAOException e) {
                LOGGER.error("RegisterUserCommand error.", e);
                page = PageContainer.ERROR_PAGE;
            }
        } else {
            session.setAttribute("error", "The data entered is not correct. Please try again.");
            page = PageContainer.ERROR_PAGE;
        }
        LOGGER.debug("finish RegisterUserCommand");
        return page;
    }

    /**
     * Validate entered parametres
     *
     * @param request
     * @return
     */
    private boolean validate(HttpServletRequest request) {
        LOGGER.debug("start boolean validate");

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        Double money = Double.parseDouble(request.getParameter("money"));
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Validator validator = Validator.getInstance();

        boolean result = validator.validateNameOrSurname(name) &&
                validator.validateNameOrSurname(surname) &&
                validator.validateMoney(money) &&
                validator.validateEmail(email) &&
                validator.validateLoginOrPassword(login) &&
                validator.validateLoginOrPassword(password);

        LOGGER.debug("finish boolean validate with: " + result);

        return result;
    }
}
