package com.cleanup.todoc.injection;

import android.content.Context;

import com.cleanup.todoc.database.SaveMyTask;
import com.cleanup.todoc.repositories.TaskDataRepository;
import com.cleanup.todoc.ui.ViewModelFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {
    public static TaskDataRepository provideTaskDataSource(Context context){
        SaveMyTask database = SaveMyTask.getInstance(context);
        return new TaskDataRepository(database.taskDao());
    }


    public static Executor provideExecutor(){ return Executors.newSingleThreadExecutor(); }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        TaskDataRepository dataSourceItem = provideTaskDataSource(context);

        Executor executor = provideExecutor();
        return new ViewModelFactory(dataSourceItem, executor);
    }
}
