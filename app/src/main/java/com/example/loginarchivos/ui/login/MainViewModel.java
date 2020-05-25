package com.example.loginarchivos.ui.login;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.loginarchivos.model.Usuario;
import com.example.loginarchivos.request.ApiClient;

public class MainViewModel extends AndroidViewModel {
    private ApiClient ac;
    private MutableLiveData<Boolean> activadorIntent;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Boolean> getActivadorIntent(){
        if(activadorIntent == null){
            activadorIntent = new MutableLiveData<>();
        }
        return activadorIntent;
    }

    public void validar(String mail, String contra){
        Usuario userXD = ac.leer(getApplication());
        if((userXD == null) || (userXD.getDni() == 0)){
            Toast.makeText(getApplication(), "Ponete las pilas flaco! Registrate", Toast.LENGTH_LONG).show();
        }else{
            Usuario userNuevito = ac.login(getApplication(), mail, contra);
            if(userNuevito != null){
                activadorIntent.setValue(true);
            }else{
                Toast.makeText(getApplication(), "Datos incorrectos", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void registrar(){
        Usuario user = new Usuario(0, "","","","");
        ac.guardar(getApplication(), user);
        activadorIntent.setValue(false);
    }


}
