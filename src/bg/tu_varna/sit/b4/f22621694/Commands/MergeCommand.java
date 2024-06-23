package bg.tu_varna.sit.b4.f22621694.Commands;

import bg.tu_varna.sit.b4.f22621694.Calendar;
import bg.tu_varna.sit.b4.f22621694.CalendarException;
/**
 * Команда за обединяване на два календара.
 */
public class MergeCommand implements Command {
    private Calendar calendar;

    public MergeCommand(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public void execute(String[] args) {
        try {
            if (args.length >= 2) {
                String calendarFile = args[1];
                calendar.merge(calendarFile);
            } else {
                System.out.println("Usage: merge <calendar>");
            }
        } catch (CalendarException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getUsage() {
        return "merge <calendar> - merges another calendar with the current one";
    }
}
