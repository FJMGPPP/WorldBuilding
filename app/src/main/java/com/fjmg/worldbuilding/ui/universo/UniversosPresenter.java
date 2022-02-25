package com.fjmg.worldbuilding.ui.universo;

import android.content.Context;

import com.fjmg.worldbuilding.data.model.Universo;

import java.util.ArrayList;

public class UniversosPresenter implements MvpUniversosContract.Presenter
{
    MvpUniversosContract.View view;
    Context context;
    MvpUniversosContract.Interactor interactor;

    public UniversosPresenter(MvpUniversosContract.View view)
    {
        this.view = view;
        this.interactor = new UniversosInteractor(this);
    }

    @Override
    public void load(String uid) {
        interactor.load(uid);
    }

    @Override
    public void agregar(Universo nuevo) {
        interactor.agregar(nuevo);
    }

    @Override
    public <T> void onSuccessLoad(ArrayList<T> universos)
    {
        view.onSuccessLoad(universos);
    }

    @Override
    public <T> void onFailLoad(ArrayList<T> universos) {
        view.onFailLoad(universos);
    }
}
