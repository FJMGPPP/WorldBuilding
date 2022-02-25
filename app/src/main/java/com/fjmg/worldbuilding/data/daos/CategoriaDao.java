package com.fjmg.worldbuilding.data.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.fjmg.worldbuilding.data.model.Categoria;

import java.util.List;

@Dao
public interface CategoriaDao {
    @Insert
    long insert(Categoria categoria);

    @Update
    void update(Categoria categoria);

    @Query("delete from categoria where id = :id")
    void delete(long id);

    @Query("select * from categoria where idUniverso = :universoId and idPadre < 0")
    List<Categoria> select(long universoId);

    @Query("select * from Categoria where idUniverso = :universoId and idPadre = :idPadre")
    List<Categoria> selectSubCategoria(long universoId, long idPadre);
}