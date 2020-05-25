package com.example.loginarchivos.request;

import android.content.Context;
import android.widget.Toast;

import com.example.loginarchivos.model.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ApiClient {
    public static File archivo;

    private static File conectar(Context context){
        archivo = new File(context.getFilesDir(), "datos.dat");
        return archivo;
    }

    public static void guardar(Context context, Usuario u){
        archivo = conectar(context);
        try{
            FileOutputStream fo = new FileOutputStream(archivo);
            BufferedOutputStream bo = new BufferedOutputStream(fo);
            DataOutputStream dos = new DataOutputStream(bo);

            dos.writeLong(u.getDni());
            dos.writeUTF(u.getApellido());
            dos.writeUTF(u.getNombre());
            dos.writeUTF(u.getEmail());
            dos.writeUTF(u.getContrasenia());

            bo.flush();
            fo.close();
        }catch (IOException io){
            Toast.makeText(context, "ERROR xD", Toast.LENGTH_LONG).show();
        }
    }
    public static Usuario leer(Context context){
        archivo = conectar(context);
        Usuario u = null;
        try{
            FileInputStream fi = new FileInputStream(archivo);
            BufferedInputStream bi = new BufferedInputStream(fi);
            DataInputStream di = new DataInputStream(bi);

            long dni = di.readLong();
            String apellido = null;
            String nombre = null;
            String email = null;
            String contrasenia = null;
            while((apellido = di.readUTF()) != null){
                nombre = di.readUTF();
                email = di.readUTF();
                contrasenia = di.readUTF();
                u = new Usuario(dni, apellido, nombre, email, contrasenia);
            }
            fi.close();

        }catch (IOException io){
            io.printStackTrace();
        }

        return u;
    }

    public static Usuario login(Context context, String mail, String contra){
        Usuario user = null;
        archivo = conectar(context);
        try{
            FileInputStream fi = new FileInputStream(archivo);
            BufferedInputStream bi = new BufferedInputStream(fi);
            DataInputStream di = new DataInputStream(bi);

            long dni = di.readLong();
            String apellido = null;
            String nombre = null;
            String email = null;
            String contrasenia = null;
            while((apellido = di.readUTF()) != null){
                nombre = di.readUTF();
                email = di.readUTF();
                contrasenia = di.readUTF();
                if(mail.equals(email) && contra.equals(contrasenia)){
                    user = new Usuario(dni, apellido, nombre, email, contrasenia);
                }
            }
            fi.close();
        }catch (IOException io){
            io.printStackTrace();
        }

        return user;
    }
}
