package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Модель для описания задачи в трекере задач
 */
public class Task {
    /**
     * Идентификатор задачи
     */
    final private int id;

    /**
     * Название задачи
     */
    private String name;

    /**
     * Статус задачи
     */
    private Status status;

    /**
     * Когда была создана задача
     */
    final private Date createdAt;

    /**
     * Когда была обновлена задача
     */
    final private Date updatedAt;

    /**
     * Конструктор в случае, если есть дата создания и дата обновления задачи
     *
     * @param id        - идентификатор
     * @param name      - название
     * @param status    - статус
     * @param createdAt - когда была создани
     * @param updatedAt - когда была обновлена
     */
    public Task(int id, String name, Status status, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.createdAt = DateUtil.getDateFromString(createdAt);
        this.updatedAt = DateUtil.getDateFromString(updatedAt);
    }

    /**
     * Конструктор для создания задачи
     *
     * @param id     - идентификатор
     * @param name   - название
     * @param status - статус
     */
    public Task(int id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    /**
     * Получение идентификатора
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Установка названия задачи
     *
     * @param name - название
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получение названия задачи
     *
     * @return string
     */
    public String getName() {
        return name;
    }

    /**
     * Получение статуса задачи
     *
     * @return Status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Получение отформатированной даты создания задачи
     *
     * @return String
     */
    public String getCreatedAt() {
        return DateUtil.getStringFromDate(this.createdAt);
    }

    /**
     * Получение отформатированной даты обновления задачи
     *
     * @return String
     */
    public String getUpdatedAt() {
        return DateUtil.getStringFromDate(this.updatedAt);
    }

    /**
     * Пометить задачу как "Завершена"
     */
    public void markDone()
    {
        this.status = Status.DONE;
    }

    /**
     * Пометить задачу как "В процессе"
     */
    public void markInProgress()
    {
        this.status = Status.IN_PROGRESS;
    }

    /**
     * Пометить задачу как "Нужно сделать"
     */
    public void markTodo()
    {
        this.status = Status.TODO;
    }
}
