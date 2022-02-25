package com.fjmg.worldbuilding.ui.login;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
 
import com.fjmg.worldbuilding.data.base.OnRepositoryCallback;
import com.fjmg.worldbuilding.data.model.User;
import com.fjmg.worldbuilding.data.repositorys.RepositoryFirebase;
import com.fjmg.worldbuilding.utils.Utils;

public class LoginInteractor implements OnRepositoryCallback
{

    private LoginContract.OnIteratorListener listener;
    private LoginContract.Repository repository;
    public void validateCredentials(User user)
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                    if (TextUtils.isEmpty(user.getEmail().trim()) && TextUtils.isEmpty(user.getPassword().trim()))
                    {
                        listener.onEmailEmptyError();
                        listener.onPasswordEmptyError();
                        return;
                    }
                    if (TextUtils.isEmpty(user.getEmail().trim()))
                    {
                        listener.onEmailEmptyError();
                        return;
                    }
                    if (TextUtils.isEmpty(user.getPassword().trim()))
                    {
                        listener.onPasswordEmptyError();
                        return;
                    }
                    if(!Utils.isEmailValid(user.getEmail()))
                    {
                        listener.onEmailError();
                        return;
                    }
                    if(!Utils.isEmailValid(user.getEmail()))
                    {
                        listener.onEmailError();
                        return;
                    }
                    if(!Utils.isPasswordValid(user.getPassword()))
                    {
                        listener.onPasswordError();
                        return;
                    }
                    repository.login(user);
            }
        },2000);
    }
    public LoginInteractor(LoginContract.OnIteratorListener listener,Context context)
    {
        this.repository = RepositoryFirebase.getInstance(this);
        this.listener =  listener;
    }

    @Override
    public void onSuccess(String msg) {
        this.listener.onSuccess(msg);
    }

    @Override
    public void onFailure(String msg) { listener.onFailure(msg); }
}
