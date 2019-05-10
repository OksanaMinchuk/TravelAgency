package by.epam.javatr.minchuk.project.controller;

import by.epam.javatr.minchuk.project.controller.command.EmptyCommand;

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
    private Map<CommandName, Command> commands = new HashMap<>();

    private CommandManager() {
        commands.put(CommandName.EMPTY_COMMAND, new EmptyCommand());
//
//
//
//
    }

    public static CommandManager getInstance() {
        return instance;
    }

    public Command getCommand(String commandName) {
        CommandName command = CommandName.valueOf(commandName.toUpperCase());
        return commands.get(command);
    }
}
