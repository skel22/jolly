package pt.ipbeja.estig.twdm.pdm1.myapplication.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CrepeRepository {
    private CrepeDao crepeDao;
    private Executor executor = Executors.newSingleThreadExecutor();

    // Constructor: Initialize the CrepeRepository with a reference to the CrepeDao
    public CrepeRepository(Context context){
        this.crepeDao = AppDatabase.getInstance(context).getCrepeDao();
    }

    // Get all Crepe items as LiveData, allowing automatic UI updates when data changes
    public LiveData<List<Crepe>> getAll() {
        return this.crepeDao.getAll();
    }

    // Get a specific Crepe item by its ID as LiveData, allowing automatic UI updates when data changes
    public LiveData<Crepe> getById(long crepeId) {
        return this.crepeDao.getById(crepeId);
    }
}