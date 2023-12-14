package pt.ipbeja.estig.twdm.pdm1.myapplication.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CartRepository {
    private CartDao cartDao;
    private Executor executor = Executors.newSingleThreadExecutor();

    // Constructor to initialize CartRepository with a CartDao instance
    public CartRepository(Context context) {
        this.cartDao = AppDatabase.getInstance(context).getCartDao();
    }

    // Get all Cart items as LiveData, allowing automatic UI updates when data changes
    public LiveData<List<Cart>> getAll() {
        return this.cartDao.getAll();
    }

    // Insert a Cart item in a background thread using Executor
    public void insert(Cart cart) {
        executor.execute(() -> cartDao.insert(cart));
    }
}
