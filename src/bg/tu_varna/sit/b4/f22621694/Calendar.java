package bg.tu_varna.sit.b4.f22621694;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Клас Calendar за управление на събития.
 */
public class Calendar implements Serializable {
    // Константи, определящи началното и крайното работно време на календара.
    public static final LocalTime START_TIME = LocalTime.of(8, 00);
    public static final LocalTime END_TIME = LocalTime.of(17, 1);
    // Списък за съхранение на събитията.
    private List<Event> events = new ArrayList<>();
    // Път на текущо отворения файл.
    private String currentFile;
    // Поток за четене на обекти от файл.
    private ObjectInputStream fStream=null;

    /**
     * Отваря файл на посочения път. Ако файлът не съществува, създава нов файл.
     *
     * @param filePath път на файла за отваряне.
     * @throws CalendarException ако има грешка при отваряне или създаване на файла.
     */
    public void open(String filePath) throws CalendarException {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File not found. Created new file: " + filePath);
                    events = new ArrayList<>();
                    saveAs(filePath);
                } else {
                    throw new CalendarException("Failed to create new file: " + filePath);
                }
            } catch (IOException e) {
                throw new CalendarException("Error creating new file: " + e.getMessage());
            }
        } else {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
                events = (List<Event>) ois.readObject();
                currentFile = filePath;
                System.out.println("Successfully opened " + filePath);
            } catch (IOException | ClassNotFoundException e) {
                throw new CalendarException("Error opening file: " + e.getMessage());
            }
        }
    }

    /**
     * Затваря текущо отворения файл и изчиства списъка със събития.
     *
     * @throws CalendarException ако няма текущо отворен файл.
     * @throws IOException ако има грешка при затваряне на файла.
     */
    public void close() throws CalendarException, IOException {
        if (currentFile == null) {
            throw new CalendarException("No file is currently open.");
        }
        events.clear();
        if(fStream!=null) fStream.close();
        currentFile = null;
        System.out.println("Successfully closed the file.");
    }

    /**
     * Запазва събитията в текущо отворения файл.
     *
     * @throws CalendarException ако има грешка при запазване на файла.
     */
    public void save() throws CalendarException {
        if (currentFile == null) {
            throw new CalendarException("No file is currently open.");
        }
        saveAs(currentFile);
    }

    /**
     * Запазва събития в нов файл, посочен от пътя.
     *
     * @param filePath път на файла за запазване.
     * @throws CalendarException ако има грешка при запазване на файла.
     */
    public void saveAs(String filePath) throws CalendarException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(events);
            currentFile = filePath;
            System.out.println("Successfully saved " + filePath);
        } catch (IOException e) {
            throw new CalendarException("Error saving file: " + e.getMessage());
        }
    }

    /**
     * Резервира ново събитие в календара.
     *
     * @param dateStr датата на събитието.
     * @param startTimeStr началното време на събитието.
     * @param endTimeStr крайното време на събитието.
     * @param name името на събитието.
     * @param note допълнителни бележки за събитието.
     * @throws CalendarException ако има грешка при запазване на файла.
     */
    public void book(String dateStr, String startTimeStr, String endTimeStr, String name, String note) throws CalendarException {
        LocalDate date = LocalDate.parse(dateStr);
        LocalTime startTime = LocalTime.parse(startTimeStr);
        LocalTime endTime = LocalTime.parse(endTimeStr);

        Event event = new Event(date, startTime, endTime, name, note);
        if (events.stream().anyMatch(e -> e.conflictsWith(event))) {
            throw new CalendarException("Conflicting event exists for the given time.");
        }
        events.add(event);
        System.out.println("Successfully booked: " + event);
    }



    /**
     * Отменя (премахва) събитие от календара.
     *
     * @param dateStr датата на събитието.
     * @param startTimeStr началното време на събитието.
     * @param endTimeStr крайното време на събитието.
     * @throws CalendarException ако има грешка при запазване на файла.
     */
    public void unbook(String dateStr, String startTimeStr, String endTimeStr) throws CalendarException {
        LocalDate date = LocalDate.parse(dateStr);
        LocalTime startTime = LocalTime.parse(startTimeStr);
        LocalTime endTime = LocalTime.parse(endTimeStr);

        boolean removed = events.removeIf(event -> event.getDate().equals(date) && event.getStartTime().equals(startTime) && event.getEndTime().equals(endTime));
        if (!removed) {
            throw new CalendarException("No event found for the given date and time.");
        }
        System.out.println("Successfully canceled the event on " + date + " from " + startTime + " to " + endTime);
    }

    /**
     * Извежда дневния ред за определена дата.
     *
     * @param dateStr датата, за която да се изведе дневния ред.
     * @throws CalendarException ако има грешка при запазване на файла.
     */
    public void agenda(String dateStr) throws CalendarException {
        LocalDate date = LocalDate.parse(dateStr);
        List<Event> dailyEvents = events.stream()
                .filter(event -> event.getDate().equals(date))
                .collect(Collectors.toList());

        if (dailyEvents.isEmpty()) {
            throw new CalendarException("No events found for " + date);
        } else {
            dailyEvents.forEach(System.out::println);
        }
    }

    /**
     * Променя детайлите на събитие.
     *
     * @param dateStr датата на събитието.
     * @param startTimeStr началното време на събитието.
     * @param option опцията за промяна.
     * @param newValue новата стойност за опцията.
     * @throws CalendarException ако има грешка при запазване на файла.
     */
    public void change(String dateStr, String startTimeStr, String option, String newValue) throws CalendarException {
        LocalDate date = LocalDate.parse(dateStr);
        LocalTime startTime = LocalTime.parse(startTimeStr);

        for (Event event : events) {
            if (event.getDate().equals(date) && event.getStartTime().equals(startTime)) {
                switch (option) {
                    case "date":
                        event.setDate(LocalDate.parse(newValue));
                        break;
                    case "starttime":
                        event.setStartTime(LocalTime.parse(newValue));
                        break;
                    case "endtime":
                        event.setEndTime(LocalTime.parse(newValue));
                        break;
                    case "name":
                        event.setName(newValue);
                        break;
                    case "note":
                        event.setNote(newValue);
                        break;
                    default:
                        throw new CalendarException("Invalid option. Valid options are: date, starttime, endtime, name, note.");
                }
                System.out.println("Successfully changed the event: " + event);
                return;
            }
        }
        throw new CalendarException("Event not found.");
    }

    /**
     * Намира събития, които съответстват на даден низ за търсене.
     *
     * @param searchString низът за търсене в събитията.
     * @throws CalendarException ако има грешка при запазване на файла.
     */
    public void find(String searchString) throws CalendarException {
        List<Event> foundEvents = events.stream()
                .filter(event -> event.getName().contains(searchString) || event.getNote().contains(searchString))
                .collect(Collectors.toList());

        if (foundEvents.isEmpty()) {
            throw new CalendarException("No events found containing: " + searchString);
        } else {
            foundEvents.forEach(System.out::println);
        }
    }

    /**
     * Отбелязва определена дата като празник.
     *
     * @param dateStr датата, която да се отбележи като празник.
     * @throws CalendarException ако има грешка при запазване на файла.
     */
    public void holiday(String dateStr) throws CalendarException {
        LocalDate date = LocalDate.parse(dateStr);
        events.add(new Event(date, LocalTime.MIN, LocalTime.MAX, "Holiday", ""));
        System.out.println("Successfully marked " + date + " as a holiday.");
    }

    /**
     * Намира заетите дни в даден период.
     *
     * @param fromDateStr началната дата на периода.
     * @param toDateStr крайната дата на периода.
     *@throws CalendarException ако има грешка при запазване на файла.
     */
    public void busyDays(String fromDateStr, String toDateStr) throws CalendarException {
        LocalDate fromDate = LocalDate.parse(fromDateStr);
        LocalDate toDate = LocalDate.parse(toDateStr);

        List<Event> periodEvents = events.stream()
                .filter(event -> !event.getDate().isBefore(fromDate) && !event.getDate().isAfter(toDate))
                .collect(Collectors.toList());

        if (periodEvents.isEmpty()) {
            throw new CalendarException("No events found in the given period.");
        } else {
            periodEvents.stream()
                    .collect(Collectors.groupingBy(Event::getDate))
                    .entrySet().stream()
                    .sorted((e1, e2) -> e2.getValue().size() - e1.getValue().size())
                    .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue().size() + " events"));
        }
    }

    /**
     * Намира свободен времеви интервал с определена продължителност, започващ от дадена дата.
     *
     * @param fromDateStr началната дата за търсене.
     * @param hours продължителността на свободния интервал в часове.
     *@throws CalendarException ако има грешка при запазване на файла.
     */
    public void findSlot(String fromDateStr, int hours) throws CalendarException {
        LocalDate fromDate = LocalDate.parse(fromDateStr);
        LocalTime startTime = START_TIME;
        LocalTime endTime = END_TIME;

        // Duration of the slot in minutes
        int slotDurationMinutes = hours * 60;

        for (LocalDate date = fromDate; date.isBefore(fromDate.plusMonths(1)); date = date.plusDays(1)) {
            LocalDate finalDate = date;

            // Filter events for the current date
            List<Event> eventsForDate = events.stream()
                    .filter(event -> event.getDate().equals(finalDate))
                    .sorted(Comparator.comparing(Event::getStartTime))
                    .collect(Collectors.toList());

            // Check if there is a slot before the first event
            if (eventsForDate.isEmpty() || eventsForDate.get(0).getStartTime().isAfter(startTime.plusMinutes(slotDurationMinutes))) {
                System.out.println("Found slot on " + finalDate + " from " + startTime + " to " + startTime.plusMinutes(slotDurationMinutes));
                return;
            }

            // Check between events
            for (int i = 0; i < eventsForDate.size() - 1; i++) {
                LocalTime endOfCurrentEvent = eventsForDate.get(i).getEndTime();
                LocalTime startOfNextEvent = eventsForDate.get(i + 1).getStartTime();

                if (endOfCurrentEvent.plusMinutes(slotDurationMinutes).isBefore(startOfNextEvent)) {
                    System.out.println("Found slot on " + finalDate + " from " + endOfCurrentEvent + " to " + endOfCurrentEvent.plusMinutes(slotDurationMinutes));
                    return;
                }
            }

            // Check if there is a slot after the last event
            LocalTime endOfLastEvent = eventsForDate.get(eventsForDate.size() - 1).getEndTime();
            if (endOfLastEvent.plusMinutes(slotDurationMinutes).isBefore(endTime)) {
                System.out.println("Found slot on " + finalDate + " from " + endOfLastEvent + " to " + endOfLastEvent.plusMinutes(slotDurationMinutes));
                return;
            }

            // Check for an entirely free day
            if (eventsForDate.isEmpty()) {
                System.out.println("Found slot on " + finalDate + " from " + startTime + " to " + startTime.plusMinutes(slotDurationMinutes));
                return;
            }
        }

        // No slot found within the next month
        throw new CalendarException("No available slot found from " + fromDate + " for " + hours + " hours.");
    }

    /**
     * Намира свободен времеви интервал с определена продължителност в координация с друг календар.
     *
     * @param fromDateStr началната дата за търсене.
     * @param hours продължителността на свободния интервал в часове.
     * @param calendarFile файлът на другия календар за координация.
     * @throws CalendarException ако има грешка при запазване на файла.
     */
    public void findSlotWith(String fromDateStr, int hours, String calendarFile) throws CalendarException {
        Calendar externalCalendar = new Calendar();
        externalCalendar.open(calendarFile);

        LocalDate fromDate = LocalDate.parse(fromDateStr);
        LocalTime startTime = START_TIME;
        LocalTime endTime = END_TIME;

        // Duration of the slot in minutes
        int slotDurationMinutes = hours * 60;

        for (LocalDate date = fromDate; !date.isAfter(fromDate.plusMonths(1).minusDays(1)); date = date.plusDays(1)) {
            LocalDate finalDate = date;

            // Get events for the current and external calendars for the current date
            List<Event> currentEvents = events.stream()
                    .filter(event -> event.getDate().equals(finalDate))
                    .sorted(Comparator.comparing(Event::getStartTime))
                    .collect(Collectors.toList());

            List<Event> externalEvents = externalCalendar.events.stream()
                    .filter(event -> event.getDate().equals(finalDate))
                    .sorted(Comparator.comparing(Event::getStartTime))
                    .collect(Collectors.toList());

            // Check for available slots within the specified time window (8 AM to 5 PM)
            LocalTime currentTime = START_TIME;
            while (currentTime.plusMinutes(slotDurationMinutes).isBefore(END_TIME)) {
                final LocalTime slotStart = currentTime;
                final LocalTime slotEnd = currentTime.plusMinutes(slotDurationMinutes);

                boolean slotAvailable = currentEvents.stream().noneMatch(event ->
                        (event.getStartTime().isBefore(slotEnd) && event.getEndTime().isAfter(slotStart))
                ) && externalEvents.stream().noneMatch(event ->
                        (event.getStartTime().isBefore(slotEnd) && event.getEndTime().isAfter(slotStart))
                );

                if (slotAvailable) {
                    System.out.println("Found slot on " + finalDate + " from " + slotStart + " to " + slotEnd);
                    return;
                }

                currentTime = currentTime.plusMinutes(30); // Move to the next potential slot
            }
        }

        // No slot found within the next month
        throw new CalendarException("No available slot found from " + fromDate + " for " + hours + " hours.");
    }

    /**
     * Обединява друг календар с текущия календар.
     *
     * @param calendarFile пътят на файла на другия календар за обединяване.
     * @throws CalendarException ако има грешка при запазване на файла.
     */
    public void merge(String calendarFile) throws CalendarException {
        Calendar externalCalendar = new Calendar();
        externalCalendar.open(calendarFile);

        for (Event externalEvent : externalCalendar.events) {
            if (events.stream().noneMatch(event -> event.conflictsWith(externalEvent))) {
                events.add(externalEvent);
                System.out.println("Merged event: " + externalEvent);
            } else {
                System.out.println("Conflict with event: " + externalEvent);
            }
        }
    }
}
