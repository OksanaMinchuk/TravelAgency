package by.epam.javatr.minchuk.project.controller;

import by.epam.javatr.minchuk.project.controller.command.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Class {@code CommandManager}
 *
 * @author Oksana Minchuk
 * @version 1.0 10/05/2019
 */

public class CommandManager {

    private static CommandManager instance = new CommandManager();
    private Map<CommandContainer, Command> commands = new HashMap<>();

    private CommandManager() {
        commands.put(CommandContainer.EMPTY_COMMAND, new EmptyCommand());
        commands.put(CommandContainer.REGISTER, new RegisterUserCommand());
        commands.put(CommandContainer.LOGIN, new LogInCommand());
        commands.put(CommandContainer.LOGOUT, new LogOutCommand());
        commands.put(CommandContainer.TO_REGISTER, new ToRegisterPageCommand());
        commands.put(CommandContainer.TO_LOGIN, new ToLoginPageCommand());
        commands.put(CommandContainer.RU, new ChangeLocale());
        commands.put(CommandContainer.EN, new ChangeLocale());
        commands.put(CommandContainer.CHANGE_LOCALE, new ChangeLocale());
        commands.put(CommandContainer.VIEW_ALL_VAUCHERS, new ViewAllVauchers());
        commands.put(CommandContainer.VIEW_VAUCHERS_BY_COUNTRY, new ViewVauchersByCountry());
        commands.put(CommandContainer.VIEW_VAUCHERS_BY_TOUR_TYPE, new ViewVauchersByTourType());
        commands.put(CommandContainer.CHOOSE_VAUCHER, new ToChooseVaucherCommand());
        commands.put(CommandContainer.BOOK_VAUCHER, new BookVaucherCommand());
//
//
//
//
    }

    public static CommandManager getInstance() {
        return instance;
    }

    public Command getCommand(String commandName) {
        CommandContainer command = CommandContainer.valueOf(commandName.toUpperCase());
        return commands.get(command);
    }
}
