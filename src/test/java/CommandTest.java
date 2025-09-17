import cuteowl.note.NoteList;
import cuteowl.command.*;
import cuteowl.task.*;
import cuteowl.storage.Storage;
import cuteowl.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private NoteList notes;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage();
        notes = new NoteList();
    }

    @Test
    public void constructor_typeOnly_setsTypeCorrectly() {
        Command cmd = new ByeCommand();
        assertTrue(cmd.isExit());
    }

    @Test
    public void execute_todo_addsTodoTask() throws Exception {
        Command cmd = new TodoCommand("Read book");
        cmd.execute(tasks, ui, storage, notes);

        assertEquals(1, tasks.size());
        assertEquals("Read book", tasks.get(0).getDescription());
        assertTrue(tasks.get(0) instanceof Todo);
    }

    @Test
    public void execute_mark_marksTask() throws Exception {
        tasks.add(new Todo("Read book"));
        MarkCommand cmd = new MarkCommand(1);
        cmd.execute(tasks, ui, storage, notes);

        assertTrue(tasks.get(0).getIsDone());
    }

    @Test
    public void execute_unmark_unmarksTask() throws Exception {
        Task task = new Todo("Finish homework");
        task.mark();
        tasks.add(task);

        UnmarkCommand cmd = new UnmarkCommand(1);
        cmd.execute(tasks, ui, storage, notes);

        assertFalse(tasks.get(0).getIsDone());
    }

}