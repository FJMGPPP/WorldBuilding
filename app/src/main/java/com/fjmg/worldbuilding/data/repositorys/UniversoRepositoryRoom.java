package com.fjmg.worldbuilding.data.repositorys;

import androidx.room.Database;

import com.fjmg.worldbuilding.data.DataBase;
import com.fjmg.worldbuilding.data.daos.UniversoDao;
import com.fjmg.worldbuilding.data.model.Universo;
import com.fjmg.worldbuilding.ui.universo.MvpUniversosContract;
import com.fjmg.worldbuilding.ui.universo.bigUniverso.MvpUniversoContract;
import com.fjmg.worldbuilding.utils.Utils;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class UniversoRepositoryRoom implements MvpUniversosContract.Repository  , MvpUniversoContract.Repository {
    static UniversoRepositoryRoom instancia;
    MvpUniversosContract.Interactor interactorList;
    MvpUniversoContract.Interactor interactorUpdate;
    ArrayList<Universo> universos = new ArrayList<>();
    UniversoDao universoDao;
    long lastId;



    UniversoRepositoryRoom()
    {
        universoDao =DataBase.getDatabase().universoDao();
        try {
            lastId = DataBase.databaseWriteExecutor.submit(()-> universoDao.getLastId()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static UniversoRepositoryRoom getInstance(MvpUniversoContract.Interactor interactor)
    {
        if (instancia == null)
        {
            instancia = new UniversoRepositoryRoom();
        }
        instancia.interactorUpdate = interactor;
        return  instancia;
    }
    public static UniversoRepositoryRoom getInstance(MvpUniversosContract.Interactor interactor)
    {
        if (instancia == null)
        {
            instancia = new UniversoRepositoryRoom();
        }
        instancia.interactorList = interactor;
        return  instancia;
    }

    @Override
    public void load(String uid)
    {
        try {
           universos = (ArrayList<Universo>) DataBase.databaseWriteExecutor.submit(()-> universoDao.select(uid)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        interactorList.onSuccessLoad(universos);
    }

    @Override
    public void agregar(Universo nuevo) {

        nuevo.setTitulo(nuevo.getTitulo()+lastId);
        try {
            lastId = DataBase.databaseWriteExecutor.submit(()-> universoDao.insert(nuevo)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        nuevo.setId(lastId);
        try {
            interactorUpdate.Success(nuevo);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    @Override
    public void ActualizarNombre(String nombre,long id) throws Exception {
        int size = instancia.universos.size();
        for (int i = 0; i < size ; i++) {
            if (instancia.universos.get(i).getId() == id)
            {
                instancia.universos.get(i).setTitulo(nombre);
                Universo update =instancia.universos.get(i);
                DataBase.databaseWriteExecutor.submit(()-> universoDao.update(update));
                interactorUpdate.Success(instancia.universos.get(i));
                return;
            }
        }

    }

    @Override
    public void ActualizarDescripcion(String descripcion,long id) throws Exception {
        int size = instancia.universos.size();
        for (int i = 0; i < size ; i++) {
            if (instancia.universos.get(i).getId() == id)
            {
                instancia.universos.get(i).setDescripcion(descripcion);
                Universo update =instancia.universos.get(i);
                DataBase.databaseWriteExecutor.submit(()-> universoDao.update(update));
                interactorUpdate.Success(instancia.universos.get(i));
                return;
            }
        }
    }

    @Override
    public void ActualizarImagen(String Base64,long id) {
        for ( Universo universo: universos)
        {
            if (universo.getId() == id) {
                universo.setImagenBitmap(Utils.BitmapDecode(Base64));
                universo.tieneImagen = true;
                DataBase.databaseWriteExecutor.submit(()-> universoDao.update(universo));
                break;
            }
        }
    }

    @Override
    public void Delete(long id) {
            int size = instancia.universos.size();
            for (int i = 0; i < size ; i++) {
                if (instancia.universos.get(i).getId() == id)
                {
                    Universo delete =instancia.universos.get(i);
                    DataBase.databaseWriteExecutor.submit(()-> universoDao.delete(delete));
                    DataBase.databaseWriteExecutor.submit(()-> universoDao.deleteAllCategoria(id));
                    return;
                }
            }

    }
}
