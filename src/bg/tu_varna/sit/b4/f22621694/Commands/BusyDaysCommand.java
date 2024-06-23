package bg.tu_varna.sit.b4.f22621694.Commands;

import bg.tu_varna.sit.b4.f22621694.Calendar;
import bg.tu_varna.sit.b4.f22621694.CalendarException;
/**
 * Команда за показване на заетите дни в определен период.
 */
public class BusyDaysCommand implements Command {
    private Calendar calendar;

    public BusyDaysCommand(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public void execute(String[] args) {
        try {
            if (args.length >= 3) {
                String fromDate = args[1];
                String toDate = args[2];
                calendar.busyDays(fromDate, toDate);
            } else {
                System.out.println("Usage: busydays <from> <to>");
            }
        } catch (CalendarException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getUsage() {
        return "busydays <from> <to> - shows busy days between dates";
    }
}
