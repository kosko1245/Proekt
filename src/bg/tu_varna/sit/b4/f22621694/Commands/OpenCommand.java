package bg.tu_varna.sit.b4.f22621694.Commands;

import bg.tu_varna.sit.b4.f22621694.Calendar;
import bg.tu_varna.sit.b4.f22621694.CalendarException;
/**
 * Команда за отваряне на файл.
 */
public class OpenCommand implements Command {
    private Calendar calendar;

    public OpenCommand(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public void execute(String[] args) {
        try {
            if (args.length > 1) {
                calendar.open(args[1]);
            } else {
                System.out.println("Please provide a file path.");
            }
        } catch (CalendarException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getUsage() {
        return "open <file> - opens <file>";
    }
}
