package com.fjmg.worldbuilding.ui.categoria;

import com.fjmg.worldbuilding.data.model.Categoria;

public class CategoriaUpdatePresenter implements MvpCategoriaUpdate.Presenter
{
    MvpCategoriaUpdate.View view;
    MvpCategoriaUpdate.Interactor interactor;
    public CategoriaUpdatePresenter(MvpCategoriaUpdate.View view)
    {
        this.view = view;
        this.interactor = new CategoriaUpdateInteractor(this);
    }

    @Override
    public void onUpdateNameSucess(String name) {
        view.onUpdateNameSucess(name);
    }

    @Override
    public void onUpdateNameFail(String msg) {
        view.onUpdateNameFail(msg);
    }

    @Override
    public void update(String name, Categoria categoria) {
        interactor.verifyName(name,categoria);
    }
}
