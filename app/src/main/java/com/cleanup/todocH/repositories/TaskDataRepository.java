package com.cleanup.todocH.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todocH.database.dao.TaskDao;
import com.cleanup.todocH.model.Task;

import java.util.List;

public class TaskDataRepository {

    private final TaskDao taskDao;

    public TaskDataRepository(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    //GET
    public LiveData<List<Task>> getTasks() {
        return this.taskDao.getTasks();
    }


    //CREATE
    public void createTask(Task task) {
        taskDao.insertTask(task);
    }

    //DELETE
    public void deleteTask(Task task) {
        taskDao.deleteTask(task);
    }

    //UPDATE
    public void updateDatabase(Task task) {
        taskDao.updateTask(task);
    }
}
