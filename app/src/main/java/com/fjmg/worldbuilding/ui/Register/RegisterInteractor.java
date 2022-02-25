package com.fjmg.worldbuilding.ui.Register;


import android.content.Context;

import com.fjmg.worldbuilding.data.model.User;
import com.fjmg.worldbuilding.data.repositorys.RepositoryFirebase;
import com.fjmg.worldbuilding.data.repositorys.RepositoryStatic;
import com.fjmg.worldbuilding.utils.Utils;

public class RegisterInteractor implements RegisterContract.Iteractor {
    private final RegisterPresenter registerPresenter;
    private final RegisterContract.Repository repository;
    public RegisterInteractor(RegisterPresenter registerPresenter) {
        this.registerPresenter = registerPresenter;
        this.repository= RepositoryFirebase.getInstance(this);
    }

    @Override
    public void validarSignUp(User user)
    {   //                  Username Valid
        //-------------------------------------------------
        if (user.getUsername().trim().isEmpty())
        {
            registerPresenter.onUsernameEmptyError();
            return;
        }
        if (!Utils.isUsernameValid(user.getUsername()))
        {
            registerPresenter.onUsernameError();
            return;
        }
        //-------------------------------------------------
        //                  Email Valid
        //-------------------------------------------------
        if (user.getEmail().trim().isEmpty())
        {
            registerPresenter.onEmailEmptyError();
            return;
        }
        if (!Utils.isEmailValid(user.getEmail()))
        {
            registerPresenter.onEmailError();
            return;
        }
        //                  Password Valid
        //-------------------------------------------------
        if (user.getPassword().trim().isEmpty())
        {
            registerPresenter.onPasswordEmptyError();
            return;
        }
        if (!Utils.isPasswordValid(user.getPassword()))
        {
            registerPresenter.onPasswordError();
            return;
        }
        //-------------------------------------------------
        //                 Match Password Valid
        //-------------------------------------------------
        if (!user.getPassword().equals(user.getMatchPassword()))
        {
            registerPresenter.onMessageDontMatch();
            return;
        }
        //-------------------------------------------------
        repository.Register(user);
    }

    @Override
    public void onSuccess(String msg) {
        registerPresenter.onSuccess(msg);
    }

    @Override
    public void onFailure(String msg) {
        registerPresenter.onFailure(msg);
    }
}
