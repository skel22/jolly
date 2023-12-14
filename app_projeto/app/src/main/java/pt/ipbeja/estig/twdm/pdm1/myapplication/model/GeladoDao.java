package pt.ipbeja.estig.twdm.pdm1.myapplication.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Gelado;

@Dao
public interface GeladoDao {

    // Query to get all Gelado items from the database as LiveData
    @Query("SELECT * FROM Gelado")
    LiveData<List<Gelado>> getAll();

    // Query to get a specific Gelado item by its ID as LiveData
    @Query("SELECT * FROM Gelado WHERE gelId = :gelId")
    LiveData<Gelado> getById(long gelId);
}
