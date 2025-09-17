package cuteowl.command;

import cuteowl.note.NoteList;
import cuteowl.storage.Storage;
import cuteowl.task.TaskList;
import cuteowl.ui.Ui;

public class ListNotesCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, NoteList notes) {
        ui.showNotesList(notes);
        return ui.showNoteListGUI(notes);
    }
}
