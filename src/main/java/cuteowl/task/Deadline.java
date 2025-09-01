package cuteowl.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task in CuteOwl chatbot application.
 * A Deadline includes a description and a due date with time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    /**
     * Constructs a Deadline task with the specified description and due date with time.
     *
     * @param description Description of the deadline task.
     * @param by The due date and time for the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task including its type
     * indicator, completion status icon and formatted due date with time.
     *
     * @return A formatted string representing the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string formatted for persistent storage.
     */
    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description +
                " | " + by.format(formatter) ;
    }

}
