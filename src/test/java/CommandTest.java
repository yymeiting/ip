import cuteowl.command.Command;
import cuteowl.task.*;
import cuteowl.storage.Storage;
import cuteowl.ui.Ui;
import cuteowl.exception.CuteOwlException;
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
        Command cmd = new Command("bye");
        assertTrue(cmd.isExit());
    }

    @Test
    public void execute_todo_addsTodoTask() throws Exception {
        Command cmd = new Command("todo", "Read book");
        cmd.execute(taskList, ui, storage);

        assertEquals(1, taskList.size());
        assertEquals("Read book", taskList.get(0).getDescription());
        assertTrue(taskList.get(0) instanceof Todo);
    }

    @Test
    public void execute_deadline_validFormat_addsDeadlineTask() throws Exception {
        String[] args = {"Submit assignment 1", "2/9/2025 1800"};
        Command cmd = new Command("deadline", args);
        cmd.execute(taskList, ui, storage);

        assertEquals(1, taskList.size());
        assertTrue(taskList.get(0) instanceof Deadline);
    }

    @Test
    public void execute_deadline_invalidFormat_throwsException() {
        String[] args = {"Submit assignment 1", "invalid-date"};
        Command cmd = new Command("deadline", args);

        Exception e = assertThrows(CuteOwlException.class, () -> cmd.execute(taskList, ui, storage));
        assertTrue(e.getMessage().contains("invalid"));
    }

    @Test
    public void execute_mark_marksTask() throws Exception {
        taskList.add(new Todo("Read book"));
        Command cmd = new Command("mark", "1");
        cmd.execute(taskList, ui, storage);

        assertTrue(taskList.get(0).getIsDone());
    }

    @Test
    public void execute_unmark_unmarksTask() throws Exception {
        Task task = new Todo("Finish homework");
        task.mark();
        taskList.add(task);

        Command cmd = new Command("unmark", "1");
        cmd.execute(taskList, ui, storage);

        assertFalse(taskList.get(0).getIsDone());
    }


    @Test
    public void execute_unknownCommand_throwsException() {
        Command cmd = new Command("unknown");
        Exception ex = assertThrows(CuteOwlException.class, () -> cmd.execute(taskList, ui, storage));
        assertTrue(ex.getMessage().contains("Unknown command"));
    }
}