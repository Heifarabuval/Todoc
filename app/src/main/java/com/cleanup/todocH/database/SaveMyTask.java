package com.cleanup.todocH.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.cleanup.todocH.database.dao.TaskDao;
import com.cleanup.todocH.model.Project;
import com.cleanup.todocH.model.Task;

@Database(entities = {Task.class, Project.class}, version = 1, exportSchema = false)

public abstract class SaveMyTask extends RoomDatabase {
    //SINGLETON
    private static volatile SaveMyTask INSTANCE;

    //DAO
    public abstract TaskDao taskDao();


    //INSTANCE
    public static SaveMyTask getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SaveMyTask.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SaveMyTask.class, "MyDatabase.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    }


