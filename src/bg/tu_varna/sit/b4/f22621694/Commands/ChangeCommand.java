package bg.tu_varna.sit.b4.f22621694.Commands;

import bg.tu_varna.sit.b4.f22621694.Calendar;
import bg.tu_varna.sit.b4.f22621694.CalendarException;
/**
 * Команда за промяна на детайли на събитие.
 */
public class ChangeCommand implements Command {
    private Calendar calendar;

    public ChangeCommand(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public void execute(String[] args) {
        try {
            if (args.length >= 5) {
                String date = args[1];
                String startTime = args[2];
                String option = args[3];
                String newValue = args[4];
                calendar.change(date, startTime, option, newValue);
            } else {
                System.out.println("Usage: change <date> <starttime> <option> <newvalue>");
            }
        } catch (CalendarException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getUsage() {
        return "change <date> <starttime> <option> <newvalue> - changes an event's details";
    }
}
