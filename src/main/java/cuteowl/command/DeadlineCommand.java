package cuteowl.command;

import cuteowl.storage.Storage;
import cuteowl.task.Deadline;
import cuteowl.task.Task;
import cuteowl.task.TaskList;
import cuteowl.task.Todo;
import cuteowl.ui.Ui;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    public final String description;
    public final LocalDateTime by;

    public DeadlineCommand(String description, LocalDateTime by) {
        assert !description.trim().isEmpty() : "Description must not be empty";
        assert by != null : "Deadline time must not be null";

        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        int initialSize = tasks.size();

        Task task = new Deadline(description, by);
        tasks.add(task);
        assert tasks.size() == initialSize + 1 : "task.size() should increase by 1";
        storage.save(tasks);
        ui.showTaskAdded(task, tasks.size());
        return ui.showTaskAddedGUI(task, tasks.size());
    }

}
