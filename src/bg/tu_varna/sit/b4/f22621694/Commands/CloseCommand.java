package bg.tu_varna.sit.b4.f22621694.Commands;

import bg.tu_varna.sit.b4.f22621694.Calendar;
import bg.tu_varna.sit.b4.f22621694.CalendarException;

import java.io.IOException;

/**
 * Команда за затваряне на текущо отворен файл.
 */
public class CloseCommand implements Command {
    private Calendar calendar;

    public CloseCommand(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public void execute(String[] args) {
        try {
            calendar.close();
        } catch (CalendarException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getUsage() {
        return "close - closes currently opened file";
    }
}
