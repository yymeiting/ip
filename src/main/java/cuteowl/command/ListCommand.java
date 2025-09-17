package cuteowl.command;

import cuteowl.storage.Storage;
import cuteowl.task.TaskList;
import cuteowl.ui.Ui;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
        return ui.showTaskListGUI(tasks);
    }
}
