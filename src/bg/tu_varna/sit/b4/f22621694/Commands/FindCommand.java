package bg.tu_varna.sit.b4.f22621694.Commands;

import bg.tu_varna.sit.b4.f22621694.Calendar;
import bg.tu_varna.sit.b4.f22621694.CalendarException;
/**
 * Команда за намиране на събития по даден низ.
 */
public class FindCommand implements Command {
    private Calendar calendar;

    public FindCommand(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public void execute(String[] args) {
        try {
            if (args.length >= 2) {
                String searchString = args[1];
                calendar.find(searchString);
            } else {
                System.out.println("Usage: find <string>");
            }
        } catch (CalendarException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getUsage() {
        return "find <string> - finds events containing the string in name or note";
    }
}
