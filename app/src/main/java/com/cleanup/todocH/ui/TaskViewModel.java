package com.cleanup.todocH.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.cleanup.todocH.model.Task;
import com.cleanup.todocH.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {
    //DATA
    @Nullable
    private LiveData<List<Task>> currentTasks;


    //REPOSITORIES
    private final TaskDataRepository taskDataRepository;
    private final Executor executor;

    public TaskViewModel(TaskDataRepository taskDataRepository, Executor executor) {
        this.taskDataRepository = taskDataRepository;
        this.executor = executor;
    }

    public void init() {
        if (this.currentTasks != null) {
            return;
        }
        currentTasks = taskDataRepository.getTasks();
    }

    public LiveData<List<Task>> getTask() {
        return this.currentTasks;
    }

    public void createTask(final Task task) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                taskDataRepository.createTask(task);
            }
        });
    }

    public void deleteTask(final Task task) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                taskDataRepository.deleteTask(task);
            }
        });
    }

    public void updateTask(final Task task) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                taskDataRepository.updateDatabase(task);
            }
        });
    }

}
