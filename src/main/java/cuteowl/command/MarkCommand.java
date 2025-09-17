package cuteowl.command;

import cuteowl.note.NoteList;
import cuteowl.exception.CuteOwlException;
import cuteowl.storage.Storage;
import cuteowl.task.Task;
import cuteowl.task.TaskList;
import cuteowl.ui.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, NoteList notes) throws CuteOwlException {
        if (index > tasks.size()) {
            throw new CuteOwlException("Please input a valid index.");
        }

        Task task = tasks.get(index - 1);
        task.mark();
        storage.save(tasks, notes);
        ui.showMarkText(task);
        return ui.showMarkTextGUI(task);
    }

}
