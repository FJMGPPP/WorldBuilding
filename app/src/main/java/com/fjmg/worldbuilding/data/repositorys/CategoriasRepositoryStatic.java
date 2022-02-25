package com.fjmg.worldbuilding.data.repositorys;

import com.fjmg.worldbuilding.data.model.Categoria;
import com.fjmg.worldbuilding.ui.categoria.SubCategoria.MvpSubCategorias;
import com.fjmg.worldbuilding.ui.main.categoria.MvpCategorias;

import java.util.ArrayList;

public class CategoriasRepositoryStatic implements MvpCategorias.Repository , MvpSubCategorias.Repository{

    private  static CategoriasRepositoryStatic instancia;
    private MvpCategorias.Presenter presenter;
    private MvpSubCategorias.Presenter subPresenter;
    ArrayList<Categoria> datos;
    public CategoriasRepositoryStatic()
    {
        datos = new ArrayList<>();
        datos.add(new Categoria(-1,"Perros"));
        datos.add(new Categoria(-1,"Gatos"));
        datos.add(new Categoria(1,"Caninos",1));
        datos.add(new Categoria(1,"Lobos",1));
        datos.add(new Categoria(1,"Otros",1));
        datos.add(new Categoria(1,"Indios",2));
        datos.add(new Categoria(1,"Europeos",2));
        datos.add(new Categoria(1,"Africanos",2));
    }

    public static MvpCategorias.Repository getInstance(MvpCategorias.Presenter presente)
    {
        if (instancia == null)
        {
            instancia = new CategoriasRepositoryStatic();
        }
        instancia.presenter = presente;
        return instancia;
    }
    public static MvpSubCategorias.Repository getInstance(MvpSubCategorias.Presenter presente)
    {
        if (instancia == null)
        {
            instancia = new CategoriasRepositoryStatic();
        }
        instancia.subPresenter = presente;
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
        ArrayList<Categoria> categoriaPorId = new ArrayList<>();
        for (Categoria categoria: datos)
        {
                if ((categoria.getIdUniverso() == id || categoria.getIdUniverso() < 0) && categoria.getIdPadre() == -1)
                {
                  if (categoria.getIdUniverso() < 0 )
                    {
                        Categoria tmp = new Categoria(id,categoria.getTitulo());
                        tmp.setId(categoria.getId());
                        tmp.setIdUniverso(id);
                        categoriaPorId.add(tmp);
                    }
                    else
                        {
                            categoriaPorId.add(categoria);
                        }
                }
        }
        presenter.loadSuccess(categoriaPorId);
    }

    @Override
    public void loadSubCategoria(long idUniverso , long idCategoria) {
        ArrayList<Categoria> categorias = new ArrayList<>();
        for (Categoria categoria: datos)
        {
            if (categoria.getIdUniverso() == idUniverso && categoria.getIdPadre() == idCategoria)
            {
                categorias.add(categoria);
            }
        }
        subCategoriaLoadSucess(categorias);
    }

    @Override
    public void add(Categoria categoria)
    {
        datos.add(categoria);
        addSuccess(categoria);
    }

    @Override
    public void delete(long id) {
        int delete = -1;
        for (Categoria categoria: datos)
        {
            delete++;
            if (categoria.getId() == id )
            {
                break;
            }
        }
        datos.remove(delete);
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
}
