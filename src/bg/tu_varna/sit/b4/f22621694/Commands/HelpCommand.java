package bg.tu_varna.sit.b4.f22621694.Commands;

import java.util.Map;
/**
 * Команда за показване на списък с поддържаните команди.
 */
public class HelpCommand implements Command {
    private Map<String, Command> commands;

    public HelpCommand(Map<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("The following commands are supported:");
        for (Map.Entry<String, Command> entry : commands.entrySet()) {
            System.out.println(entry.getValue().getUsage());
        }
    }

    @Override
    public String getUsage() {
        return "help - prints this information";
    }
}
