package cuteowl.note;

public class Note {
    private final String content;

    public Note(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }

    public String toSaveFormat() {
        return content;
    }
}