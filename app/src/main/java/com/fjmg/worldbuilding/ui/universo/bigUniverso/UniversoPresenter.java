package com.fjmg.worldbuilding.ui.universo.bigUniverso;

import com.fjmg.worldbuilding.data.model.Universo;

public class UniversoPresenter implements MvpUniversoContract.Presenter {
    private MvpUniversoContract.View view;
    private MvpUniversoContract.Interactor interactor;
    public UniversoPresenter(MvpUniversoContract.View view)
    {
        this.view = view;
        this.interactor = new UniversoInteractor(this);
    }

    @Override
    public void onInvalidName(String error)
    {
        view.setInvalidName(error);
    }

    @Override
    public void onInvalidDescription(String error) {
        view.setInvalidDescription(error);
    }

    @Override
    public void onInvalidImagen(String error) throws Exception {
        view.setInvalidImagen(error);
    }

    @Override
    public void Success(Universo universo) throws Exception {
        view.Success(universo);
    }

    @Override
    public void fail(String error) {
        view.fail(error);
    }

    @Override
    public void ActualizarNombre(String nombre,long id) throws Exception {
        interactor.validateName(nombre,id);
    }

    @Override
    public void ActualizarDescripcion(String descripcion,long id) throws Exception {
        interactor.ActualizarDescripcion(descripcion,id);
    }

    @Override
    public void ActualizarImagen(String Base64,long id) {
        interactor.validateImage(Base64,id);
    }

    @Override
    public void Delete(long id) {
        interactor.Delete(id);
    }
}
