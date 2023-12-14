package pt.ipbeja.estig.twdm.pdm1.myapplication.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Crepe;

@Dao
public interface CrepeDao {
    // Retrieve all Crepe items as LiveData, allowing automatic UI updates when data changes
    @Query("SELECT * FROM crepe")
    LiveData<List<Crepe>> getAll();

    // Retrieve a specific Crepe item by its ID as LiveData, allowing automatic UI updates when data changes
    @Query("SELECT * FROM crepe WHERE crepeId = :crepeId")
    LiveData<Crepe> getById(long crepeId);
}
