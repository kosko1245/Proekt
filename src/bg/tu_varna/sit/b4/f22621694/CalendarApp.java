package bg.tu_varna.sit.b4.f22621694;
import bg.tu_varna.sit.b4.f22621694.Calendar;
import bg.tu_varna.sit.b4.f22621694.Commands.Command;
import bg.tu_varna.sit.b4.f22621694.Commands.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CalendarApp {
    private static Calendar calendar = new Calendar();
    /**
     * Клас CalendarApp за управление на приложението за личен календар.
     */
    public static void main(String[] args) {
        // Карта за съхранение на командите и съответните им изпълнения.
        Map<String, Command> commands = new HashMap<>();
        commands.put("open", new OpenCommand(calendar));
        commands.put("close", new CloseCommand(calendar));
        commands.put("save", new SaveCommand(calendar));
        commands.put("saveas", new SaveAsCommand(calendar));
        commands.put("help", new HelpCommand(commands));
        commands.put("exit", new ExitCommand());
        commands.put("book", new BookCommand(calendar));
        commands.put("unbook", new UnbookCommand(calendar));
        commands.put("agenda", new AgendaCommand(calendar));
        commands.put("change", new ChangeCommand(calendar));
        commands.put("find", new FindCommand(calendar));
        commands.put("holiday", new HolidayCommand(calendar));
        commands.put("busydays", new BusyDaysCommand(calendar));
        commands.put("findslot", new FindSlotCommand(calendar));
        commands.put("findslotwith", new FindSlotWithCommand(calendar));
        commands.put("merge", new MergeCommand(calendar));
        // Скенер за четене на вход от потребителя.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Personal Calendar App. Type 'help' for a list of commands.");
        // Безкраен цикъл за обработка на команди от потребителя.
        while (true) {
            System.out.print("> ");
            String commandLine = scanner.nextLine().trim();
            if (commandLine.isEmpty()) continue;

            String[] commandParts = commandLine.split(" ");
            String command = commandParts[0].toLowerCase();

            Command commandAction = commands.get(command);
            if (commandAction != null) {
                try {
                    commandAction.execute(commandParts);
                } catch (Exception e) {
                    System.out.println("Error executing command: " + e.getMessage());
                }
            } else {
                System.out.println("Unknown command. Type 'help' for a list of commands.");
            }
        }
    }
}
