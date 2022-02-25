package com.fjmg.worldbuilding.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.fjmg.worldbuilding.data.model.Categoria;
import com.fjmg.worldbuilding.data.daos.CategoriaDao;
import com.fjmg.worldbuilding.data.model.Universo;
import com.fjmg.worldbuilding.data.daos.UniversoDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Universo.class, Categoria.class},version = 1)
public abstract class DataBase extends RoomDatabase {

    public abstract CategoriaDao categoriaDao();
    public abstract UniversoDao universoDao();
    private static volatile DataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    static DataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DataBase.class, "worldbuilding")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

  
    public static void create(final Context context) {
        if (INSTANCE == null) {
            synchronized (DataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DataBase.class, "worldbuilding")
                            .build();
                }
            }
        }
    }

    public static DataBase getDatabase() {
        return INSTANCE;

    }
}
