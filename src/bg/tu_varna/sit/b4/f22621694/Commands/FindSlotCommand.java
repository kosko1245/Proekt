package bg.tu_varna.sit.b4.f22621694.Commands;

import bg.tu_varna.sit.b4.f22621694.Calendar;
import bg.tu_varna.sit.b4.f22621694.CalendarException;
/**
 * Команда за намиране на свободен времеви интервал.
 */
public class FindSlotCommand implements Command {
    private Calendar calendar;

    public FindSlotCommand(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public void execute(String[] args) {
        try {
            if (args.length >= 3) {
                String fromDate = args[1];
                int hours = Integer.parseInt(args[2]);
                calendar.findSlot(fromDate, hours);
            } else {
                System.out.println("Usage: findslot <fromdate> <hours>");
            }
        } catch (CalendarException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getUsage() {
        return "findslot <fromdate> <hours> - finds free slot for a meeting";
    }
}
