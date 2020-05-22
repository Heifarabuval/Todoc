package com.cleanup.todoc.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task ")
    LiveData<List<Task>> getTasks();
    @Query("SELECT * FROM Task ")
    LiveData<List<Task>> getTasksWithCursor();

    @Query("SELECT * FROM Task ")
    LiveData<List<Task>> getTaskWithId();


    @Insert
    long insertTask(Task task);

    @Update
    int updateTask(Task task);

    @Delete
    int deleteTask(Task task);
}
