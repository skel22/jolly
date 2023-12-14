package pt.ipbeja.estig.twdm.pdm1.myapplication.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

// Repository class for Waffle entity, acts as a communication layer between ViewModel and DAO
public class WaffleRepository {
    private WaffleDao waffleDao; // Data Access Object for Waffle entity
    private Executor executor = Executors.newSingleThreadExecutor(); // Executor for background tasks

    // Constructor to initialize the repository with the Waffle DAO
    public WaffleRepository(Context context){
        this.waffleDao = AppDatabase.getInstance(context).getWaffleDao();
    }

    // Get all Waffle items from the database as LiveData
    public LiveData<List<Waffle>> getAll() {
        return this.waffleDao.getAll();
    }

    // Get a specific Waffle item by its ID from the database as LiveData
    public LiveData<Waffle> getWaffleById(long waffleId) {
        return this.waffleDao.getById(waffleId);
    }
}