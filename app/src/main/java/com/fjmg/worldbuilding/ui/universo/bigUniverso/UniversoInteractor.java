package com.fjmg.worldbuilding.ui.universo.bigUniverso;

import androidx.core.graphics.BitmapCompat;

import com.fjmg.worldbuilding.data.model.Universo;
import com.fjmg.worldbuilding.data.repositorys.UniversoRepositoryRoom;
import com.fjmg.worldbuilding.data.repositorys.UniversoRepositoryStatic;
import com.fjmg.worldbuilding.utils.Utils;

public class UniversoInteractor implements MvpUniversoContract.Interactor
{
    MvpUniversoContract.Presenter presenter;
    MvpUniversoContract.Repository repository;
    public UniversoInteractor(MvpUniversoContract.Presenter presenter)
    {
        this.presenter = presenter;
        repository = UniversoRepositoryRoom.getInstance(this);
    }


    @Override
    public void Success(Universo universo) throws Exception {
        presenter.Success(universo);
    }

    @Override
    public void fail(String error) {
        presenter.fail(error);
    }

    @Override
    public void ActualizarNombre(String nombre,long id) throws Exception {
        repository.ActualizarNombre(nombre,id);
    }

    @Override
    public void ActualizarDescripcion(String descripcion,long id) throws Exception {
        repository.ActualizarDescripcion(descripcion,id);
    }

    @Override
    public void ActualizarImagen(String Base64,long id) {
        repository.ActualizarImagen(Base64,id);
    }

    @Override
    public void Delete(long id) {
        repository.Delete(id);
    }

    @Override
    public void validateName(String nombre,long id) throws Exception {
        if (nombre.length() < 5)
        {
            presenter.onInvalidName("minima");
            fail("No puede ser menor a 5 el titulo");
            return;
        }
        repository.ActualizarNombre(nombre, id);
    }

    @Override
    public void validateImage(String base64,long id) {

        repository.ActualizarImagen(base64,id);
    }
}
