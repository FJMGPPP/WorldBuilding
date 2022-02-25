package com.fjmg.worldbuilding.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.fjmg.worldbuilding.data.model.Universo;

import java.util.List;

@Dao
public interface UniversoDao
{
    @Insert
    long insert(Universo universo);

    @Update
    void update(Universo universo);

    @Delete
    void delete(Universo universo);

    @Query("delete from universo")
    void deleteAll();

    @Query("select * from universo where uidUsuario = :uidUsuario")
    List<Universo> select(String uidUsuario);

    @Query("delete from categoria where idUniverso = :idUniverso")
    void deleteAllCategoria(long idUniverso);
    @Query("SELECT id FROM universo ORDER BY id DESC LIMIT 1")
    long getLastId();
}
