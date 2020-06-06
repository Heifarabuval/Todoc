package com.cleanup.todocH.injection;

import android.content.Context;

import com.cleanup.todocH.database.SaveMyTask;
import com.cleanup.todocH.repositories.TaskDataRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {

    private static TaskDataRepository provideTaskDataSource(Context context) {
        SaveMyTask database = SaveMyTask.getInstance(context);
        return new TaskDataRepository(database.taskDao());
    }


    private static Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        TaskDataRepository dataSourceItem = provideTaskDataSource(context);

        Executor executor = provideExecutor();
        return new ViewModelFactory(dataSourceItem, executor);
    }
}
