package pt.ipbeja.estig.twdm.pdm1.myapplication.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

// Repository class to handle data operations for Toppings
public class ToppingsRepository {

    private ToppingsDao toppingsDao;
    private Executor executor = Executors.newSingleThreadExecutor();

    // Constructor to initialize the ToppingsDao and Executor
    public ToppingsRepository(Context context){
        this.toppingsDao = AppDatabase.getInstance(context).getToppingsDao();
    }

    // Method to retrieve all Toppings items from the database as LiveData
    public LiveData<List<Toppings>> getAll() { return this.toppingsDao.getAll(); }

    // Method to retrieve a specific Toppings item by its ID from the database as LiveData
    public LiveData<Toppings> getByID(long topId) { return this.toppingsDao.getById(topId); }
}
