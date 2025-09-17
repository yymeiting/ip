package cuteowl.command;

import cuteowl.note.NoteList;
import cuteowl.exception.CuteOwlException;
import cuteowl.storage.Storage;
import cuteowl.task.Task;
import cuteowl.task.TaskList;
import cuteowl.ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, NoteList notes) throws CuteOwlException {
        int initialSize = tasks.size();

        if (index > tasks.size()) {
            System.out.println("     Please input a valid index.");
            throw new CuteOwlException("Please input a valid index.");
        }
        Task removed = tasks.delete(index - 1);
        assert tasks.size() == initialSize - 1 : "task.size() should decrease by 1";
        storage.save(tasks, notes);
        ui.showTaskDeleted(removed, tasks.size());
        return ui.showTaskDeletedGUI(removed, tasks.size());
    }
}
