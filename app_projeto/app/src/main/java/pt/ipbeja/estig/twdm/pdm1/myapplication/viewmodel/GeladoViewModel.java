package pt.ipbeja.estig.twdm.pdm1.myapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Cart;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.CartRepository;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Gelado;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.GeladoRepository;

public class GeladoViewModel extends AndroidViewModel {
    private GeladoRepository geladoRepository;
    private CartRepository cartRepository;

    public GeladoViewModel(@NonNull Application application){
        super(application);
        this.geladoRepository = new GeladoRepository(application.getApplicationContext());
    }

    public LiveData<List<Gelado>> getAll() { return geladoRepository.getAll(); }

    public LiveData<Gelado> getGelById(long gelId) { return geladoRepository.getById(gelId); }

    public void addToCart(Cart cart) { cartRepository.insert(cart); }
}