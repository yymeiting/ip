package cuteowl.command;

import cuteowl.note.Note;
import cuteowl.note.NoteList;
import cuteowl.storage.Storage;
import cuteowl.task.TaskList;
import cuteowl.ui.Ui;

public class AddNoteCommand extends Command{
    private final String content;

    public AddNoteCommand(String content) {
        this.content = content;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, NoteList notes) {
        Note note = new Note(content);
        notes.add(note);
        storage.save(tasks, notes);
        ui.showNoteAdded(note);
        return ui.showNoteAddedGUI(note);
    }
}
