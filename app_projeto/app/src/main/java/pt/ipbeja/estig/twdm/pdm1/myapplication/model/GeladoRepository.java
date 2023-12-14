package pt.ipbeja.estig.twdm.pdm1.myapplication.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GeladoRepository {

    // GeladoDao instance to interact with the Gelado database table
    private GeladoDao geladoDao;

    // Executor for performing database operations on a separate thread
    private Executor executor = Executors.newSingleThreadExecutor();

    // Constructor to initialize GeladoRepository with the GeladoDao
    public GeladoRepository(Context context){
        this.geladoDao = AppDatabase.getInstance(context).getGeladoDao();
    }

    // Retrieve all Gelado items from the database as LiveData
    public LiveData<List<Gelado>> getAll() {
        return this.geladoDao.getAll();
    }

    // Retrieve a specific Gelado item by its ID from the database as LiveData
    public LiveData<Gelado> getById(long gelId) {
        return this.geladoDao.getById(gelId);
    }
}
