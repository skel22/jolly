package pt.ipbeja.estig.twdm.pdm1.myapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Cart;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.CartRepository;

public class CartViewModel extends AndroidViewModel {
    private CartRepository cartRepository;
    public CartViewModel(@NonNull Application application){
        super(application);
        this.cartRepository = new CartRepository(application.getApplicationContext());
    }
    public LiveData<List<Cart>> getAll() { return cartRepository.getAll(); }

    public void insert(Cart cart) { cartRepository.insert(cart); }
}
