package com.fjmg.worldbuilding.ui.categoria.SubCategoria;

import com.fjmg.worldbuilding.data.model.Categoria;
import com.fjmg.worldbuilding.data.repositorys.CategoriasRepositoryRoom;

import java.util.ArrayList;

public class SubCategoriaPresenter implements MvpSubCategorias.Presenter
{
    MvpSubCategorias.View view;
    MvpSubCategorias.Repository repository;
    public SubCategoriaPresenter(MvpSubCategorias.View view) {
    this.view = view;
    this.repository = CategoriasRepositoryRoom.getInstance(this);
    }

    @Override
    public void subCategoriaLoadSucess(ArrayList<Categoria> categorias) {
        view.subCategoriaLoadSucess(categorias);
    }

    @Override
    public void subCategoriaAddSuccess(Categoria categoria) {
        view.subCategoriaAddSuccess(categoria);
    }

    @Override
    public void subCategoriaDeleteSucess(long id) {

        view.subCategoriaDeleteSucess(id);
    }


    @Override
    public void loadSubCategoria(long idUniverso, long idCategoriaPadre) {
        repository.loadSubCategoria(idUniverso,idCategoriaPadre);
    }

    @Override
    public void add(Categoria categoria) {
        repository.add(categoria);
    }

    @Override
    public void delete(long id) {
        repository.delete(id);
    }
}
