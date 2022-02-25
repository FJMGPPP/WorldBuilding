package com.fjmg.worldbuilding.ui.categoria;

import com.fjmg.worldbuilding.data.model.Categoria;
import com.fjmg.worldbuilding.data.repositorys.CategoriasRepositoryRoom;

public class CategoriaUpdateInteractor implements MvpCategoriaUpdate.Interactor {
    MvpCategoriaUpdate.Presenter presenter;
    MvpCategoriaUpdate.Repository repository;
    public CategoriaUpdateInteractor(MvpCategoriaUpdate.Presenter presenter)
    {
    this.presenter = presenter;
    this.repository = CategoriasRepositoryRoom.getInstance(this);
    }

    @Override
    public void verifyName(String name,Categoria categoria) {
        if (name == null || name.isEmpty() || name.length() > 10 || name.length() < 5)
        {
            onUpdateNameFail("Invalido");
        }
        else
            {
                update(name,categoria);
            }
    }

    @Override
    public void onUpdateNameSucess(String name)
    {
        presenter.onUpdateNameSucess(name);
    }

    @Override
    public void onUpdateNameFail(String msg) {
        presenter.onUpdateNameFail(msg);
    }

    @Override
    public void update(String name, Categoria categoria) {
        repository.update(name, categoria);
    }
}
