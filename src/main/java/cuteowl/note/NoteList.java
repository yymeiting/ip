package cuteowl.note;

import java.util.ArrayList;
import java.util.List;

public class NoteList {
    private final List<Note> notes = new ArrayList<>();

    public void add(Note note) {
        notes.add(note);
    }

    public List<Note> getAll() {
        return notes;
    }

    public Note get(int index) {
        return notes.get(index);
    }

    public int size() {
        return notes.size();
    }

    public boolean isEmpty() {
        return notes.isEmpty();
    }
}

