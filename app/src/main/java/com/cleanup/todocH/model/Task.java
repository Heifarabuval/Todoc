package com.cleanup.todocH.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Comparator;


@Entity
public class Task {
    public void setId(long id) {
        this.id = id;
    }


    @PrimaryKey(autoGenerate = true)
    private long id;



    @ForeignKey(entity = Project.class, parentColumns = "id", childColumns = "projectId")
    private long projectId;


    @SuppressWarnings("NullableProblems")
    @NonNull
    private String name;


    private long creationTimestamp;


    public Task(long projectId, @NonNull String name, long creationTimestamp) {
        this.setProjectId(projectId);
        this.setName(name);
        this.setCreationTimestamp(creationTimestamp);
    }


    public long getId() {
        return id;
    }

  
    private void setProjectId(long projectId) {
        this.projectId = projectId;
    }


    @Nullable
    public Project getProject() {
        return Project.getProjectById(projectId);
    }

 
    @NonNull
    public String getName() {
        return name;
    }


    private void setName(@NonNull String name) {
        this.name = name;
    }


    private void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public long getProjectId() {
        return projectId;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }



    public static class TaskAZComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            return left.name.compareTo(right.name);
        }
    }


    public static class TaskZAComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            return right.name.compareTo(left.name);
        }
    }


    public static class TaskRecentComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            return (int) (right.creationTimestamp - left.creationTimestamp);
        }
    }


    public static class TaskOldComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            return (int) (left.creationTimestamp - right.creationTimestamp);
        }
    }


}
