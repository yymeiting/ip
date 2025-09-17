package cuteowl.command;

import cuteowl.storage.Storage;
import cuteowl.task.Task;
import cuteowl.task.TaskList;
import cuteowl.task.Todo;
import cuteowl.ui.Ui;

public class TodoCommand extends Command {
    public final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int initialSize = tasks.size();
        Task task = new Todo(description);
        tasks.add(task);
        assert tasks.size() == initialSize + 1 : "tasks.size() should increase by 1";
        storage.save(tasks);
        ui.showTaskAdded(task, tasks.size());
        return ui.showTaskAddedGUI(task, tasks.size());
    }
}
