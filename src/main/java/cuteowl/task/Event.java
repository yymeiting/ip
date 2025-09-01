package cuteowl.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task in CuteOwl chatbot application.
 * An Event includes a description, start and end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    /**
     * Constructs an Event task with the specified description, start and end time.
     *
     * @param description Description of the event.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public Event (String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task which includes
     * its type indicator, completion status icon and formatted time range.
     *
     * @return A formatted string representing the Event task.
     */
    @Override
    public String toString () {
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) +")";
    }

    /**
     * Returns a string representation for the Event task.
     *
     * @return A string formatted for persistent storage.
     */
    @Override
    public String toSaveFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description +
                " | " + from.format(formatter) + "-" + to.format(formatter);
    }

}
