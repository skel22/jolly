package pt.ipbeja.estig.twdm.pdm1.myapplication.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Cart;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.CartRepository;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Crepe;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.CrepeRepository;

public class CrepeViewModel extends AndroidViewModel {
    private CrepeRepository crepeRepository;
    private CartRepository cartRepository;

    public CrepeViewModel(@NonNull Application application) {
        super(application);
        this.crepeRepository = new CrepeRepository(application.getApplicationContext());
    }

    public LiveData<List<Crepe>> getAll() {
        return crepeRepository.getAll();
    }

    public LiveData<Crepe> getCrepeById(long crepeId){
        return crepeRepository.getById(crepeId);
    }

    public void addToCart(Cart cart) {
        cartRepository.insert(cart);
    }
}
