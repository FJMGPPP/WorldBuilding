package com.fjmg.worldbuilding.ui.Register;


import com.fjmg.worldbuilding.data.base.BasePresenter;
import com.fjmg.worldbuilding.data.base.OnRepositoryCallback;
import com.fjmg.worldbuilding.data.model.User;
import com.fjmg.worldbuilding.ui.login.LoginContract;

public interface RegisterContract
{
        interface View extends LoginContract.View, OnRepositoryCallback
        {

            void setMessageDontMatch();

            void setUsernameEmptyError();

            void setUsernameError();


        }
        interface Presenter extends OnRepositoryCallback, BasePresenter
        {
            void onEmailEmptyError();

            void onEmailError();

            void onPasswordEmptyError();

            void onPasswordError();

            void onUsernameEmptyError();

            void onUsernameError();

            void onMessageDontMatch();

            void validarSignUp(User user);

        }
        interface Iteractor extends OnRepositoryCallback
        {
            void validarSignUp(User user);
        }
        interface Repository
        {
           void Register(User user);
        }

}
