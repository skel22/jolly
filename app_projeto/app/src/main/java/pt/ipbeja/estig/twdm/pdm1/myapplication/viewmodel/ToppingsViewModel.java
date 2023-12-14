package pt.ipbeja.estig.twdm.pdm1.myapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Cart;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.CartRepository;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Toppings;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.ToppingsRepository;

public class ToppingsViewModel extends AndroidViewModel {
    private ToppingsRepository toppingsRepository;
    private CartRepository cartRepository;

    public ToppingsViewModel(@NonNull Application application){
        super(application);
        this.toppingsRepository = new ToppingsRepository(application.getApplicationContext());
    }

    public LiveData<List<Toppings>> getAll() { return toppingsRepository.getAll(); }

    public LiveData<Toppings> getTopById(long topId) { return toppingsRepository.getByID(topId); }

    public void addToCart(Cart cart) { cartRepository.insert(cart); }
}
