package bg.tu_varna.sit.b4.f22621694.Commands;

import bg.tu_varna.sit.b4.f22621694.Calendar;
import bg.tu_varna.sit.b4.f22621694.CalendarException;
/**
 * Команда за отмяна на събитие.
 */
public class UnbookCommand implements Command {
    private Calendar calendar;

    public UnbookCommand(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public void execute(String[] args) {
        try {
            if (args.length >= 4) {
                String date = args[1];
                String startTime = args[2];
                String endTime = args[3];
                calendar.unbook(date, startTime, endTime);
            } else {
                System.out.println("Usage: unbook <date> <starttime> <endtime>");
            }
        } catch (CalendarException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getUsage() {
        return "unbook <date> <starttime> <endtime> - cancels an event";
    }
}
