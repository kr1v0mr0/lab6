package lab5.Server.Managers;

import lab5.Common.Commands.Command;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CommandManager {
    private final Map<String, Command> commands = new LinkedHashMap();
    private final List<String> commandHistory = new ArrayList<>();


   /* public CommandManager() {
        final Map<String, Command> commands = new HashMap<>();
        final List<String> commandHistory = Collections.synchronizedList(new ArrayList<>());
    }*/

    public void register(String commandName, Command command) {
        this.commands.put(commandName, command);
    }

    public Map<String, Command> getCommands() {
        return this.commands;
    }
    public List<String> getCommandHistory() {
        if(commandHistory.size()<8) { return commandHistory;}
        return commandHistory.subList(commandHistory.size()-8,commandHistory.size());
    }
    public void addToHistory(String command) {
        commandHistory.add(command);
    }
}