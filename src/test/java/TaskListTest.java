// cuteowl.task;

import cuteowl.task.TaskList;
import cuteowl.task.Todo;
import cuteowl.task.Task;
import cuteowl.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void addTask_validTask_successfullyAdded() {
        TaskList list = new TaskList();
        Task task = new Todo("Read book");
        list.add(task);
        assertEquals(1, list.size());
        assertEquals("Read book", list.get(0).getDescription());
    }


    @Test
    public void delete_validIndex_taskRemoved() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("Read book");
        Task task2 = new Deadline("Assignment 1", LocalDateTime.now());
        taskList.add(task1);
        taskList.add(task2);

        Task removed = taskList.delete(0);
        assertEquals(task1, removed);
        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.get(0));
    }


    @Test
    public void getTask_outOfBounds_throwsException() {
        TaskList list = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }
}