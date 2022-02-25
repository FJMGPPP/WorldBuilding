package com.fjmg.worldbuilding.ui.categoria.SubCategoria;

import com.fjmg.worldbuilding.data.model.Categoria;

import java.util.ArrayList;

//Debido a que no necesito validacion ninguna he saltado la interfaz interactor
public interface MvpSubCategorias
{
    interface View extends Acciones , Callback
    {


    }
    interface Presenter extends Acciones , Callback
    {

    }
    interface Repository extends Acciones , Callback
    {

    }
    interface Callback
    {
       void subCategoriaLoadSucess(ArrayList<Categoria> categorias);
       void subCategoriaAddSuccess(Categoria categoria);
       void subCategoriaDeleteSucess(long id);
    }
    interface Acciones
    {
        void loadSubCategoria(long idUniverso, long idCategoriaPadre);
        void add(Categoria categoria);
        void delete(long id);
    }
    interface Adapter
    {
        void update(ArrayList<Categoria> categorias);
        void update(Categoria categoria);
        void delete(long id);
        void order();
    }
}


