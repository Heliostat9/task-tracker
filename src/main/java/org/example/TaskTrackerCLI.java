package org.example;

public class TaskTrackerCLI {
    private TaskTracker taskTracker;

    public TaskTrackerCLI(String[] args) {
        taskTracker = new TaskTracker("example.json");

        parse(args);

        taskTracker.save();
    }

    private void parse(String[] commands)
    {
        switch (commands[0]) {
            case "list":
                if (commands.length > 1) {
                    taskTracker.list(Status.getStatus(commands[1]));
                } else {
                    taskTracker.list();
                }
                break;
            case "add":
                taskTracker.add(commands[1]);
                break;
            case "delete":
                taskTracker.remove(Integer.parseInt(commands[1]));
                break;
            case "update":
                int id = Integer.parseInt(commands[1]);
                taskTracker.update(id, commands[2]);
                break;
            case "mark-in-progress":
                taskTracker.markInProgress(Integer.parseInt(commands[1]));
                break;
            case "mark-done":
                taskTracker.markDone(Integer.parseInt(commands[1]));
                break;
        }
    }
}
