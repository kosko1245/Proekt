package bg.tu_varna.sit.b4.f22621694.Commands;

import bg.tu_varna.sit.b4.f22621694.Calendar;
import bg.tu_varna.sit.b4.f22621694.CalendarException;
/**
 * Команда за резервиране на ново събитие.
 */
public class BookCommand implements Command {
    private Calendar calendar;

    public BookCommand(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public void execute(String[] args) {
        try {
            if (args.length >= 6) {
                String date = args[1];
                String startTime = args[2];
                String endTime = args[3];
                String name = args[4];
                StringBuilder noteBuilder = new StringBuilder();
                for (int i = 5; i < args.length; i++) {
                    noteBuilder.append(args[i]);
                    if (i < args.length - 1) {
                        noteBuilder.append(" ");
                    }
                }
                String note = noteBuilder.toString();
                calendar.book(date, startTime, endTime, name, note);
            } else {
                System.out.println("Usage: book <date> <starttime> <endtime> <name> <note>");
            }
        } catch (CalendarException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getUsage() {
        return "book <date> <starttime> <endtime> <name> <note> - books an event";
    }
}
