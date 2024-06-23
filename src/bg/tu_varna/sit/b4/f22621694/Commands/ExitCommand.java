package bg.tu_varna.sit.b4.f22621694.Commands;
/**
 * Команда за изход от програмата.
 */
public class ExitCommand implements Command {

    @Override
    public void execute(String[] args) {
        System.out.println("Exiting the program...");
        System.exit(0);
    }

    @Override
    public String getUsage() {
        return "exit - exits the program";
    }
}

