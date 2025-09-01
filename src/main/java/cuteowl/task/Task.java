package cuteowl.task;

/**
 * Represents a generic task with a description and completion status
 * This class serves as the base class for more specific task types. (i.e. todo, deadline, event)
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon representing task completion
     * "X" indicates completion while a blank space indicates it is not done.
     *
     * @return A string representing the task's status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns whether task is completed.
     *
     * @return True if task is completed, false otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the tasks,
     * including its status icon and description.
     *
     * @return A formatted string representing the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string representation of the task suitable for saving to file.
     * Subclasses overrides this method to provide specific formats.
     *
     * @return A string formatted for persistent storage
     */
    public String toSaveFormat() {
        return "";
    }

    /**
     * Returns the description of the task
     *
     * @return The task's description
     */
    public String getDescription() {
        return description;
    }

}
