package bg.tu_varna.sit.b4.f22621694.Commands;

import bg.tu_varna.sit.b4.f22621694.Calendar;
import bg.tu_varna.sit.b4.f22621694.CalendarException;
/**
 * Команда за запазване на текущия календар в нов файл.
 */
public class SaveAsCommand implements Command {
    private Calendar calendar;

    public SaveAsCommand(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public void execute(String[] args) {
        try {
            if (args.length > 1) {
                calendar.saveAs(args[1]);
            } else {
                System.out.println("Please provide a file path.");
            }
        } catch (CalendarException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getUsage() {
        return "saveas <file> - saves the currently open file in <file>";
    }
}
