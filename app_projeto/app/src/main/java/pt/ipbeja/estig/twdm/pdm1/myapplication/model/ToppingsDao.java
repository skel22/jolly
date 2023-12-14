package pt.ipbeja.estig.twdm.pdm1.myapplication.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

// Data Access Object (DAO) interface for Toppings entity
@Dao
public interface ToppingsDao {

    // Query to retrieve all Toppings items from the database as LiveData
    @Query("SELECT * FROM Toppings")
    LiveData<List<Toppings>> getAll();

    // Query to retrieve a specific Toppings item by its ID from the database as LiveData
    @Query("SELECT * FROM toppings WHERE topId = :topId")
    LiveData<Toppings> getById(long topId);
}
