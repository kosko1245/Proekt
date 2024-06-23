package bg.tu_varna.sit.b4.f22621694.Commands;

import bg.tu_varna.sit.b4.f22621694.Calendar;
import bg.tu_varna.sit.b4.f22621694.CalendarException;
/**
 * Команда за намиране на свободен времеви интервал в координация с друг календар.
 */
public class FindSlotWithCommand implements Command {
    private Calendar calendar;

    public FindSlotWithCommand(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public void execute(String[] args) {
        try {
            if (args.length >= 4) {
                String fromDate = args[1];
                int hours = Integer.parseInt(args[2]);
                String calendarFile = args[3];
                calendar.findSlotWith(fromDate, hours, calendarFile);
            } else {
                System.out.println("Usage: findslotwith <fromdate> <hours> <calendar>");
            }
        } catch (CalendarException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getUsage() {
        return "findslotwith <fromdate> <hours> <calendar> - finds free slot considering another calendar";
    }
}
