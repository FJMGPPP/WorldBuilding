package com.fjmg.worldbuilding.data.repositorys;


import com.fjmg.worldbuilding.data.base.OnRepositoryCallback;
import com.fjmg.worldbuilding.data.model.TipoSuccesAndFails;
import com.fjmg.worldbuilding.data.model.User;
import com.fjmg.worldbuilding.ui.Register.RegisterContract;
import com.fjmg.worldbuilding.ui.login.LoginContract;

import java.util.ArrayList;

public class RepositoryStatic implements LoginContract.Repository , RegisterContract.Repository
{
    private OnRepositoryCallback callback;
    private  static RepositoryStatic instance;
    private ArrayList<User> users;
    private RepositoryStatic()
    {
        users = new ArrayList<>();
        initialice();
    }

    private void initialice()
    {
        users.add(new User("prueba@gmail.com","PruebaBelica21!"));
        users.add(new User("prueba2@gmail.com","PruebaBelica21!"));
        users.add(new User("prueba3@gmail.com","PruebaBelica21!"));
        users.add(new User("prueba4@gmail.com","PruebaBelica21!"));

    }

    @Override
    public void login(User user)
    {
        for (User userMemory : users)
        {
            if (user.getEmail().equals(userMemory.getEmail()) && user.getPassword().equals(userMemory.getPassword()))
            {
                callback.onSuccess(TipoSuccesAndFails.INICIO_SESION);
                return;
            }
        }
        callback.onFailure(TipoSuccesAndFails.INICIO_SESION);
    }
    public static RepositoryStatic getInstance(OnRepositoryCallback listener)
    {
        if (instance == null)
        {
            instance = new RepositoryStatic();
        }
        instance.callback = listener;
        return  instance;

    }


    @Override
    public void Register(User user) {
        for (User userMemory : users)
        {
            if (user.getEmail().equals(userMemory.getEmail()))
            {
                callback.onFailure(TipoSuccesAndFails.EMAIL_EXISTENTE);
                return;
            }
            if (user.getUsername().equals(userMemory.getUsername()))
            {
                callback.onFailure(TipoSuccesAndFails.USUARIO_EXISTENTE);
                return;
            }
        }
        users.add(user);
        callback.onSuccess(TipoSuccesAndFails.CUENTA_CREADA);
    }
}
