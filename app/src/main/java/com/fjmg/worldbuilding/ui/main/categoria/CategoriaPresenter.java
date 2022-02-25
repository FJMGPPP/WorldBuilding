package com.fjmg.worldbuilding.ui.main.categoria;

import com.fjmg.worldbuilding.data.model.Categoria;
import com.fjmg.worldbuilding.data.repositorys.CategoriasRepositoryRoom;

import java.util.ArrayList;

public class CategoriaPresenter implements MvpCategorias.Presenter
{
    MvpCategorias.View view;
    MvpCategorias.Repository repository;
    public CategoriaPresenter(MvpCategorias.View view) {
    this.view = view;
    this.repository = CategoriasRepositoryRoom.getInstance(this);
    }

    @Override
    public void loadSuccess(ArrayList<Categoria> categorias) {
        view.loadSuccess(categorias);
    }

    @Override
    public void addSuccess(Categoria categoria) {
        view.addSuccess(categoria);
    }

    @Override
    public void deleteSucess(long id) {
        view.deleteSucess(id);
    }

    @Override
    public void load(long id) {
    repository.load(id);
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
