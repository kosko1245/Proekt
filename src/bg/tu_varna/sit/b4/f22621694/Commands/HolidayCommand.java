package bg.tu_varna.sit.b4.f22621694.Commands;

import bg.tu_varna.sit.b4.f22621694.Calendar;
import bg.tu_varna.sit.b4.f22621694.CalendarException;
/**
 * Команда за маркиране на дата като празник.
 */
public class HolidayCommand implements Command {
    private Calendar calendar;

    public HolidayCommand(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public void execute(String[] args) {
        try {
            if (args.length >= 2) {
                String date = args[1];
                calendar.holiday(date);
            } else {
                System.out.println("Usage: holiday <date>");
            }
        } catch (CalendarException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getUsage() {
        return "holiday <date> - marks the date as a holiday";
    }
}
