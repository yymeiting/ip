package cuteowl.command;

import cuteowl.storage.Storage;
import cuteowl.task.Event;
import cuteowl.task.Task;
import cuteowl.task.TaskList;
import cuteowl.ui.Ui;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    public final String description;
    public final LocalDateTime from;
    public final LocalDateTime to;

    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        assert !description.trim().isEmpty() : "Description must not be empty";
        assert from != null : "Start time must not be null";
        assert to != null : "End time must not be null";

        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int initialSize = tasks.size();

        Task task = new Event(description, from, to);
        tasks.add(task);

        assert tasks.size() == initialSize + 1 : "task.size() should increase by 1";

        storage.save(tasks);
        ui.showTaskAdded(task, tasks.size());
        return ui.showTaskAddedGUI(task, tasks.size());
    }

}
