package pt.ipbeja.estig.twdm.pdm1.myapplication.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

// Data Access Object (DAO) interface for Waffle entity
@Dao
public interface WaffleDao {

    // Query to retrieve all Waffle items from the database as LiveData
    @Query("SELECT * FROM waffle")
    LiveData<List<Waffle>> getAll();

    // Query to retrieve a specific Waffle item by its ID from the database as LiveData
    @Query("SELECT * FROM waffle WHERE waffleId = :waffleId")
    LiveData<Waffle> getById(long waffleId);
}
