package cuteowl.command;

import cuteowl.note.NoteList;
import cuteowl.storage.Storage;
import cuteowl.ui.Ui;
import cuteowl.exception.CuteOwlException;
import cuteowl.task.*;


public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage, NoteList notes) throws CuteOwlException;

    public boolean isExit() {
        return false;
    }
}
