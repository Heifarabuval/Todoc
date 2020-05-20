package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskDataRepository {

    private final TaskDao taskDao;

    public TaskDataRepository(TaskDao taskDao) { this.taskDao = taskDao; }

    //GET
    public LiveData<List<Task>> getTasks(){return this.taskDao.getTasks();}

    public LiveData<List<Task>> getTasksWithId(long id){return this.taskDao.getTaskWithId();}
    //CREATE
    public void createTask(Task task){ taskDao.insertTask(task);}

    //DELETE
    public void deleteTask(long taskId){taskDao.deleteTask(taskId);}

    //UPDATE
    public void updateDatabase(Task task){taskDao.updateTask(task);}
}
