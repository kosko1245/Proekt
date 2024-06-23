package bg.tu_varna.sit.b4.f22621694.Commands;

import bg.tu_varna.sit.b4.f22621694.Calendar;
import bg.tu_varna.sit.b4.f22621694.CalendarException;
/**
 * Команда за извеждане на дневния ред за определена дата.
 */
public class AgendaCommand implements Command {
    private Calendar calendar;

    public AgendaCommand(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public void execute(String[] args) {
        try {
            if (args.length >= 2) {
                String date = args[1];
                calendar.agenda(date);
            } else {
                System.out.println("Usage: agenda <date>");
            }
        } catch (CalendarException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getUsage() {
        return "agenda <date> - shows all events for the specified date";
    }
}
