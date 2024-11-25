package org.example;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Модель для работы с трекером задач
 */
public class TaskTracker
{
    /**
     * Счетчике идентификаторов
     */
    public static int counterId = 0;

    /**
     * Задачи
     */
    private ArrayList<Task> tasks;

    /**
     * Json файл с данными
     */
    private String filename;

    /**
     * Конструктор для инициализации трекера задач
     */
    public TaskTracker() {
        tasks = new ArrayList<>();
    }

    /**
     * Конструктор с использование файла json с задачами
     *
     * @param filePath - файл с задачами
     */
    public TaskTracker(String filePath) {
        filename = filePath;
        Gson gson = GsonConfig.createGson();
        JsonReader reader = null;

        try {
            URL resource = getClass().getClassLoader().getResource("example.json");
            File file = new File(resource.getFile());
            reader = new JsonReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        if (reader == null) {
            tasks = new ArrayList<>();
            return;
        }

        try {
            Task[] jsonTasks = gson.fromJson(reader, Task[].class);
            tasks = new ArrayList<>(Arrays.asList(jsonTasks));
            counterId = tasks.get(tasks.size() - 1).getId();
        } catch (Exception e) {
            System.out.println("Error parsing json");
            tasks = new ArrayList<>();
        }
    }

    /**
     * Добавление задачи по имени
     *
     * @param name - название задачи
     */
    public void add(String name)
    {
        increaseId();

        Task task = new Task(counterId, name, Status.TODO);
        tasks.add(task);

        System.out.printf("Task added successfully (ID: %s)\n", task.getId());
    }

    /**
     * Удаление задачи по идентификатору
     *
     * @param id - идентификатор задачи
     */
    public void remove(int id)
    {
        Task task = find(id);

        if (task != null) {
            decreaseId();
            tasks.remove(find(id));
        }
    }

    /**
     * Поиск задачи по ее идентификаторы
     *
     * @param id - идентификатор задачи
     *
     * @return Task
     */
    public Task find(int id)
    {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }

        return null;
    }

    /**
     * Обновления названия задачи
     *
     * @param id   - идентификатор задачи
     * @param name - название задачи
     */
    public void update(int id, String name)
    {
        Task task = find(id);

        if (task != null) {
            task.setName(name);
        }
    }

    /**
     * Вывод всех задач
     */
    public void list()
    {
        checkTasks();

        for (Task task : tasks) {
            print(task);
        }
    }

    /**
     * Вывод задач по статусу
     *
     * @param status - статус
     */
    public void list(Status status)
    {
        checkTasks();

        for (Task task : tasks) {
            if (task.getStatus().equals(status)) {
                print(task);
            }
        }
    }

    /**
     * Найти и пометить как "В процессе"
     *
     * @param id - идентификатор задачи
     */
    public void markInProgress(int id)
    {
        Task task = find(id);
        if (task != null) {
            task.markInProgress();
        }
    }

    /**
     * Найти и пометить как "Нужно сделать"
     *
     * @param id - идентификатор задачи
     */
    public void markTodo(int id)
    {
        Task task = find(id);

        if (task != null) {
            task.markTodo();
        }
    }

    /**
     * Найти и пометить как "Завершено"
     *
     * @param id - идентификатор задачи
     */
    public void markDone(int id)
    {
        Task task = find(id);

        if (task != null) {
            task.markDone();
        }
    }

    /**
     * Проверка задач
     */
    private void checkTasks()
    {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found");
        }
    }

    /**
     * Вывести информацию о задаче
     *
     * @param task - задача
     */
    private void print(Task task)
    {
        System.out.printf("task %d: %s - %s\n", task.getId(), task.getName(), task.getStatus().getDescription());
    }

    /**
     * Увеличиваем счетчик идентификаторов
     */
    private static void increaseId()
    {
        counterId++;
    }

    /**
     * Ументшаем счетчик идентифиакторов
     */
    private static void decreaseId()
    {
        counterId--;
    }

    public void save()
    {
        try (Writer writer = new FileWriter(filename)) {
            Gson gson = GsonConfig.createGson();
            gson.toJson(tasks, writer);
        } catch (Exception e) {
            System.out.println("Error saving tasks");
        }
    }
}