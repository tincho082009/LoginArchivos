package com.example.loginarchivos.ui.registro;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.loginarchivos.model.Usuario;
import com.example.loginarchivos.request.ApiClient;

public class RegistroViewModel extends AndroidViewModel {
    private ApiClient ac;
    private MutableLiveData<Usuario> user;
    private MutableLiveData<String> noUser;
    private MutableLiveData<Boolean> activadorIntent;

    public RegistroViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Boolean> getActivadorIntent(){
        if(activadorIntent == null){
            activadorIntent = new MutableLiveData<>();
        }
        return activadorIntent;
    }
    public LiveData<Usuario> getUser(){
        if(user == null){
            user = new MutableLiveData<>();
        }
        return user;
    }

    public LiveData<String> getNoUser(){
        if(noUser == null){
            noUser = new MutableLiveData<>();
        }
        return noUser;
    }

    public void guardar(String dni, String apellido, String nombre, String email, String contrasenia){

        int dni1 = Integer.parseInt(dni);
        Usuario u = new Usuario(dni1, nombre, apellido, email, contrasenia);
        ac.guardar(getApplication(), u);
        Toast.makeText(getApplication(), "Datos guardados correctamente", Toast.LENGTH_LONG).show();
        activadorIntent.setValue(true);
    }

    public void cargarDatos(){
        Usuario u = ac.leer(getApplication());
        if(u.getEmail() == "" && u.getContrasenia() == ""){
            noUser.setValue("");
        }else{
            user.setValue(u);
        }
    }
}
