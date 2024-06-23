package bg.tu_varna.sit.b4.f22621694;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Event
 */
public class Event implements Serializable {
    // Дата на събитието.
    private LocalDate date;
    // Начално време на събитието.
    private LocalTime startTime;
    // Крайно време на събитието.
    private LocalTime endTime;
    // Име на събитието.
    private String name;
    // Бележка за събитието.
    private String note;

    /**
     * Конструктор за създаване на събитие.
     *
     * @param date датата на събитието.
     * @param startTime началното време на събитието.
     * @param endTime крайното време на събитието.
     * @param name името на събитието.
     * @param note бележката за събитието.
     */
    public Event(LocalDate date, LocalTime startTime, LocalTime endTime, String name, String note) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.note = note;
    }

    /**
     * Връща датата на събитието.
     *
     * @return датата на събитието.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Задава датата на събитието.
     *
     * @param date новата дата на събитието.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }


    /**
     * Връща началното време на събитието.
     *
     * @return началното време на събитието.
     */
    public LocalTime getStartTime() {
        return startTime;
    }
    /**
     * Задава началното време на събитието.
     *
     * @param startTime новото начално време на събитието.
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Връща крайното време на събитието.
     *
     * @return крайното време на събитието.
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Задава крайното време на събитието.
     *
     * @param endTime новото крайно време на събитието.
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    /**
     * Връща името на събитието.
     *
     * @return името на събитието.
     */
    public String getName() {
        return name;
    }

    /**
     * Задава името на събитието.
     *
     * @param name новото име на събитието.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Връща бележката на събитието.
     *
     * @return бележката на събитието.
     */
    public String getNote() {
        return note;
    }
    /**
     * Задава бележката на събитието.
     *
     * @param note новата бележка на събитието.
     */
    public void setNote(String note) {
        this.note = note;
    }
    /**
     * Проверява дали има конфликт с друго събитие.
     *
     * @param other другото събитие.
     * @return true ако има конфликт, false в противен случай.
     */
    public boolean conflictsWith(Event other) {
        return this.date.equals(other.date) &&
                (this.startTime.isBefore(other.endTime) && other.startTime.isBefore(this.endTime));
    }

    /**
     * Връща низово представяне на събитието.
     *
     * @return низово представяне на събитието.
     */
    @Override
    public String toString() {
        return "Event{" +
                "date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", name='" + name + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}

