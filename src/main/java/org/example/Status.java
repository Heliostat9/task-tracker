package org.example;

import com.google.gson.annotations.SerializedName;

/**
 * Статусы задач в таск трекере
 */
public enum Status {
    /**
     * Статус "Готово"
     */
    @SerializedName("done")
    DONE("done"),

    /**
     * Статус "Нужно сделать"
     */
    @SerializedName("todo")
    TODO("todo"),

    /**
     * Статус "В процессе"
     */
    @SerializedName("in-progress")
    IN_PROGRESS("in-progress");

    /**
     * Описание статуса
     */
    private final String description;

    /**
     * Конструктор для описания статуса
     *
     * @param description - описание
     */
    Status(String description) {
        this.description = description;
    }

    /**
     * Получение описания
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Получение статуса по названию
     *
     * @param status - статус
     *
     * @return Status
     */
    public static Status getStatus(String status) {
        switch (status) {
            case "done":
                return DONE;
            case "todo":
                return TODO;
            case "in-progress":
                return IN_PROGRESS;
        }

        return null;
    }
}
