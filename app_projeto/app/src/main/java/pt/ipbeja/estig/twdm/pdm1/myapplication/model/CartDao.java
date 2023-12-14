package pt.ipbeja.estig.twdm.pdm1.myapplication.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CartDao {

    // Retrieve all non-zero Cart items from the database
    @Query("SELECT * FROM Cart WHERE amount > 0")
    LiveData<List<Cart>> getAll();

    // Insert a new Cart item into the database
    @Insert
    void insert(Cart cart);

    // Calculate the total price of all items in the Cart
    @Query("SELECT SUM(price * amount) FROM Cart")
    double getTotalPrice();

    // Delete a Cart item from the database
    @Delete
    void delete(Cart cart);

    // Update the amount of a specific Cart item in the database
    @Query("UPDATE Cart SET amount = :amount WHERE itemId = :itemId")
    void updateAmount(int amount, int itemId);
}
