package com.fjmg.worldbuilding.data.repositorys;

import android.content.Context;
import android.util.Log;


import com.fjmg.worldbuilding.data.base.OnRepositoryCallback;
import com.fjmg.worldbuilding.data.model.TipoSuccesAndFails;
import com.fjmg.worldbuilding.data.model.User;
import com.fjmg.worldbuilding.ui.Register.RegisterContract;
import com.fjmg.worldbuilding.ui.login.Event;
import com.fjmg.worldbuilding.ui.login.LoginContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class RepositoryFirebase implements LoginContract.Repository , RegisterContract.Repository
{
    private OnRepositoryCallback callback;
    private  static RepositoryFirebase instance;

    public RepositoryFirebase() {
       registerEvent();
    }
    @Subscribe
    public void registerEvent()
        {
            EventBus.getDefault().register(this);
        }
    @Subscribe
    @Override
    public void login(User user)
    {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener((OnCompleteListener<AuthResult>) task -> {
                    String TAG = "Login firebase";

                    if (task.isSuccessful()) {

                        Log.d(TAG, "signInWithEmail:success");
                        callback.onSuccess(mAuth.getUid());
                    }
                    else
                        {
                            Event ErrorEvent = new Event();
                            ErrorEvent.setType(Event.TypesErrorEvents.INICIO_SESION_FAIL);
                            ErrorEvent.setMessage(TipoSuccesAndFails.INICIO_SESION);
                            EventBus.getDefault().post(ErrorEvent);
                            //callback.onFail(TipoSuccesAndFails.INICIO_SESION);
                        }
                });
    }
    @Subscribe
    @Override
    public void Register(User user)
    {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener((OnCompleteListener<AuthResult>) task -> {
                    String TAG = "Register firebase";
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "RegisterWithEmail:success");
                        callback.onSuccess(TipoSuccesAndFails.CUENTA_CREADA);
                    }
                    else
                    {
                        callback.onFailure(TipoSuccesAndFails.USUARIO_EXISTENTE);
                    }
                });
    }

    public static RepositoryFirebase getInstance(OnRepositoryCallback callback)
    {
        if (instance == null)
        {
            instance = new RepositoryFirebase();
        }
        instance.callback = callback;
        return  instance;

    }


}
