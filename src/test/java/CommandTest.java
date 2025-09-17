import cuteowl.command.*;
import cuteowl.task.*;
import cuteowl.storage.Storage;
import cuteowl.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage();
    }

    @Test
    public void constructor_typeOnly_setsTypeCorrectly() {
        Command cmd = new ByeCommand();
        assertTrue(cmd.isExit());
    }

    @Test
    public void execute_todo_addsTodoTask() throws Exception {
        Command cmd = new TodoCommand("Read book");
        cmd.execute(taskList, ui, storage);

        assertEquals(1, taskList.size());
        assertEquals("Read book", taskList.get(0).getDescription());
        assertTrue(taskList.get(0) instanceof Todo);
    }

    @Test
    public void execute_mark_marksTask() throws Exception {
        taskList.add(new Todo("Read book"));
        MarkCommand cmd = new MarkCommand(1);
        cmd.execute(taskList, ui, storage);

        assertTrue(taskList.get(0).getIsDone());
    }

    @Test
    public void execute_unmark_unmarksTask() throws Exception {
        Task task = new Todo("Finish homework");
        task.mark();
        taskList.add(task);

        UnmarkCommand cmd = new UnmarkCommand(1);
        cmd.execute(taskList, ui, storage);

        assertFalse(taskList.get(0).getIsDone());
    }

}