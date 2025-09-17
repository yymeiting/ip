package cuteowl.command;

import cuteowl.note.NoteList;
import cuteowl.storage.Storage;
import cuteowl.task.TaskList;
import cuteowl.ui.Ui;

public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, NoteList notes) {
        ui.showExit();
        return ui.exitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
