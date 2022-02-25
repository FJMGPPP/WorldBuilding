package com.fjmg.worldbuilding.ui.universo.bigUniverso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;

import com.fjmg.worldbuilding.data.model.Universo;

public interface MvpUniversoContract
{
    interface View extends Callback , AccionesPrincipales
    {
       void setInvalidName(String error);
       void setInvalidDescription(String error);
       void setInvalidImagen(String error) throws Exception;

    }
    interface Presenter extends Callback , AccionesPrincipales
    {
        void onInvalidName(String error);
        void onInvalidDescription(String error);
        void onInvalidImagen(String error) throws Exception;
    }
    interface Interactor extends Callback , AccionesPrincipales
    {
        void validateName(String nombre,long id) throws Exception;
        void validateImage(String base64,long id);
    }
    interface Repository extends  AccionesPrincipales
    {

    }
    interface Callback
    {
        void Success(Universo universo) throws Exception;
        void fail(String error);
    }
    interface AccionesPrincipales
    {
        void ActualizarNombre(String nombre,long id) throws Exception;
        void ActualizarDescripcion(String descripcion,long id) throws Exception;
        void ActualizarImagen(String Base64,long id);
        void Delete(long id);
    }
}
