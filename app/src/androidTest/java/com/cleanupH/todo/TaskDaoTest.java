package com.cleanupH.todo;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todocH.database.SaveMyTask;
import com.cleanup.todocH.model.Task;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    //FOR DATA
    private SaveMyTask database;

    //DATA SET FOR TEST
    private static final String TASK_NAME = "CleanTheRoom";
    private static final long CREATION_TIME_STAMP = 485;
    private static final long PROJECT_ID = 1L;
    private static final Task TASK_TEST = new Task(PROJECT_ID, TASK_NAME, CREATION_TIME_STAMP);
    private static final Task TASK_TEST2 = new Task(2L, "Réapprovisionner", CREATION_TIME_STAMP);
    private static final Task TASK_TEST3 = new Task(3L, "Changer poubelles", CREATION_TIME_STAMP);
    private static final Task TASK_DEMO = new Task(PROJECT_ID, TASK_NAME, CREATION_TIME_STAMP);
    private static final Task NEW_TASK = new Task(PROJECT_ID, TASK_NAME, CREATION_TIME_STAMP);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDatabase() {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                SaveMyTask.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDatabase() {
        database.close();
    }

    @Test
    public void insertAndGetTask() throws InterruptedException {
        //BEFORE : Adding a new task
        this.database.taskDao().insertTask(NEW_TASK);
        List<Task> task = LiveDataTestUtil.getValue(this.database.taskDao().getTasks());
        System.out.println("NAME : " + task.get(0).getName());
        assertTrue("probleme les noms ne correspondent pas", task.get(task.size() - 1).getName().equals(TASK_DEMO.getName()));

    }

    @Test
    public void deleteAndCheckTask() throws InterruptedException {
        //BEFORE : Adding a new task
        this.database.taskDao().deleteTask(TASK_DEMO);
        List<Task> task = LiveDataTestUtil.getValue(this.database.taskDao().getTasks());
        assertFalse(task.contains(TASK_DEMO));

    }

    @Test
    public void getTaskWhenNoItemInserted() throws InterruptedException {
        // TEST
        List<Task> items = LiveDataTestUtil.getValue(this.database.taskDao().getTasks());
        assertTrue(items.isEmpty());
    }

    @Test
    public void insertAndGetTasks() throws InterruptedException {
        // BEFORE : Adding demo user & demo items


        this.database.taskDao().insertTask(TASK_TEST);
        this.database.taskDao().insertTask(TASK_TEST2);
        this.database.taskDao().insertTask(TASK_TEST3);


        // TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTasks());
        assertTrue(tasks.size() == 3);
    }


}
