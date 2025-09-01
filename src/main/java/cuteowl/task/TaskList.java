package cuteowl.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the CuteOwl chatbot application.
 * Provides methods to add, remove, retrieve, and inspect tasks
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks An Array of Task objects to initialize the list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index
     *
     * @param index Index of the task to be removed
     * @return The Task that was removed
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index Index of the task to retrieve
     * @return The Task at the given index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the entire list of tasks.
     *
     * @return An ArrayList containing all tasks
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }

}
