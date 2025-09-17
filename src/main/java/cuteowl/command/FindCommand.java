package cuteowl.command;

import cuteowl.storage.Storage;
import cuteowl.task.Task;
import cuteowl.task.TaskList;
import cuteowl.ui.Ui;

public class FindCommand extends Command {
    public final String description;

    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks.getAll()) {
            if (task.getDescription().contains(description)) {
                matchingTasks.add(task);
            }
        }
        ui.showTaskList(matchingTasks);
        return ui.showTaskListGUI(matchingTasks);
    }

}
