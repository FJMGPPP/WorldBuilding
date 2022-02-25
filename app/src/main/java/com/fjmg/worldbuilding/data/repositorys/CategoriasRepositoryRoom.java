package com.fjmg.worldbuilding.data.repositorys;

import com.fjmg.worldbuilding.data.DataBase;
import com.fjmg.worldbuilding.data.daos.CategoriaDao;
import com.fjmg.worldbuilding.data.model.Categoria;
import com.fjmg.worldbuilding.ui.categoria.MvpCategoriaUpdate;
import com.fjmg.worldbuilding.ui.categoria.SubCategoria.MvpSubCategorias;
import com.fjmg.worldbuilding.ui.main.categoria.MvpCategorias;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

public class CategoriasRepositoryRoom implements MvpCategorias.Repository , MvpSubCategorias.Repository , MvpCategoriaUpdate.Repository{

    private  static CategoriasRepositoryRoom instancia;
    private MvpCategorias.Presenter presenter;
    private MvpSubCategorias.Presenter subPresenter;
    private MvpCategoriaUpdate.Interactor  interactorUpdate;
    ArrayList<Categoria> categorias;
    ArrayList<Categoria> subcategorias;
    CategoriaDao categoriaDao;
    CategoriasRepositoryRoom()
    {
        categoriaDao = DataBase.getDatabase().categoriaDao();
    }
    public static MvpCategorias.Repository getInstance(MvpCategorias.Presenter presente)
    {
        if (instancia == null)
        {
            instancia = new CategoriasRepositoryRoom();
        }
        instancia.presenter = presente;
        return instancia;
    }
    public static MvpSubCategorias.Repository getInstance(MvpSubCategorias.Presenter presente)
    {
        if (instancia == null)
        {
            instancia = new CategoriasRepositoryRoom();
        }
        instancia.subPresenter = presente;
        return instancia;
    }
    public static MvpCategoriaUpdate.Repository getInstance(MvpCategoriaUpdate.Interactor interactor)
    {
        if (instancia == null)
        {
            instancia = new CategoriasRepositoryRoom();
        }
        instancia.interactorUpdate = interactor;
        return instancia;
    }
    @Override
    public void loadSuccess(ArrayList<Categoria> categorias) {
        presenter.loadSuccess(categorias);
    }


    @Override
    public void addSuccess(Categoria categoria) {
        presenter.addSuccess(categoria);
    }

    @Override
    public void deleteSucess(long id) {
        presenter.deleteSucess(id);
    }

    @Override
    public void load(long id) {
        try {
            categorias  = (ArrayList<Categoria>) DataBase.databaseWriteExecutor.submit(()->categoriaDao.select(id)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (categorias == null)
        {
            categorias = new ArrayList<>();
        }
        presenter.loadSuccess(categorias);
    }

    @Override
    public void loadSubCategoria(long idUniverso , long idCategoria) {
        try {
            subcategorias  = (ArrayList<Categoria>) DataBase.databaseWriteExecutor.submit(()->categoriaDao.selectSubCategoria(idUniverso,idCategoria)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (subcategorias == null)
        {
            subcategorias = new ArrayList<>();
        }
        presenter.loadSuccess(subcategorias);
    }

    @Override
    public void add(Categoria categoria)
    {
        ArrayList<Categoria>categoriasBeforeInsert = null;
        try {
            categoria.setId(DataBase.databaseWriteExecutor.submit(()-> categoriaDao.insert(categoria)).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addSuccess(categoria);
    }
    @Override
    public void delete(long id) {
        DataBase.databaseWriteExecutor.submit(()-> categoriaDao.delete(id));
        Iterator<Categoria> i= categorias.iterator();
        while (i.hasNext()) {
            Categoria categoria = i.next(); // must be called before you can call i.remove()
            if (categoria.getId() == id)
            {
                i.remove();
            }
        }
        deleteSucess(id);
    }

    @Override
    public void subCategoriaLoadSucess(ArrayList<Categoria> categorias) {
        subPresenter.subCategoriaLoadSucess(categorias);
    }

    @Override
    public void subCategoriaAddSuccess(Categoria categoria) {
        subPresenter.subCategoriaAddSuccess(categoria);
    }

    @Override
    public void subCategoriaDeleteSucess(long id) {
        subPresenter.subCategoriaDeleteSucess(id);
    }

    @Override
    public void onUpdateNameSucess(String name) {
        interactorUpdate.onUpdateNameSucess(name);
    }

    @Override
    public void onUpdateNameFail(String msg) {
        interactorUpdate.onUpdateNameFail(msg);
    }

    @Override
    public void update(String name,Categoria categoria) {
        categoria.setTitulo(name);
        DataBase.databaseWriteExecutor.submit(()-> categoriaDao.update(categoria));
        interactorUpdate.onUpdateNameSucess(name);
    }
}
