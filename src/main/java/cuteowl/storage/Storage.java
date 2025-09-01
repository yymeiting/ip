package cuteowl.storage;

import cuteowl.task.Task;
import cuteowl.task.TaskList;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Handles loading and saving tasks in a local file for persistence.
 * The file is located at "data/cuteOwl.txt"
 */
public class Storage {
    Path filePath = Paths.get("data", "cuteOwl.txt");

    /**
     * Loads tasks from the save file. If the file does not exist, it is created.
     *
     * @return An ArrayList of task loaded from the file.
     * Returns an empty list if the file is new or unreadable.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            // if file does not exist, create file
            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
                return tasks;
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the current list of tasks to the save file.
     *
     * @param taskList The TaskList containing all tasks to be saved.
     */
    public void save(TaskList taskList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()));
            for (Task task : taskList.getAll()) {
                writer.write(task.toSaveFormat());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

}
