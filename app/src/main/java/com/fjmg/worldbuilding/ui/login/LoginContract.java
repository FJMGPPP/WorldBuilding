package com.fjmg.worldbuilding.ui.login;


import com.fjmg.worldbuilding.data.base.BasePresenter;
import com.fjmg.worldbuilding.data.base.IProgressView;
import com.fjmg.worldbuilding.data.base.OnRepositoryCallback;
import com.fjmg.worldbuilding.data.model.User;

public interface LoginContract
{
    interface View extends OnRepositoryCallback, IProgressView
    {
        void onEvent(Event event);

        /**
         * Alternativas del caso de uso. set porque se modifica elementos de la vista
         */
        void setEmailEmptyError();

        void setPasswordEmptyError();

        void setEmailError();

        void setPasswordError();
    }
    interface OnIteratorListener extends  OnRepositoryCallback
    {
        void onEmailEmptyError();
        void onPasswordEmptyError();
        void onEmailError();
        void onPasswordError();
    }
    interface Presenter extends  OnRepositoryCallback , BasePresenter
    {
        void validateCredentials(User user);
    }

    interface Repository
    {
        void login(User user);
    }

}

