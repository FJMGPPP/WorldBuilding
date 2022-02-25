package com.fjmg.worldbuilding.ui.universo;


import android.content.Context;

import com.fjmg.worldbuilding.data.model.Universo;
import com.fjmg.worldbuilding.data.repositorys.UniversoRepositoryRoom;
import com.fjmg.worldbuilding.data.repositorys.UniversoRepositoryStatic;
import com.fjmg.worldbuilding.ui.universo.bigUniverso.MvpUniversoContract;

import java.util.ArrayList;

 class UniversosInteractor implements MvpUniversosContract.Interactor {
    MvpUniversosContract.Presenter presenter;
    MvpUniversosContract.Repository repository;
    public UniversosInteractor(MvpUniversosContract.Presenter presenter ) {
        this.presenter = presenter;
        repository=  UniversoRepositoryRoom.getInstance(this);
    }

    @Override
    public void load(String uid) {
        repository.load(uid);
    }

     @Override
     public void agregar(Universo nuevo) {
         repository.agregar(nuevo);
     }

     @Override
    public <T> void onSuccessLoad(ArrayList<T> universos) {
    presenter.onSuccessLoad(universos);
    }

    @Override
    public <T> void onFailLoad(ArrayList<T> universos) {
        presenter.onFailLoad(universos);
    }

 }
