package com.fjmg.worldbuilding.data.base;

import java.util.List;

public interface OnRepositoryListCallback {

    void onFailure(String message);

    //El tipo es generito, en algun momento de la implementacion tendremos que
    //cambiar el T por en este caso, depedecias.
    <T>void onSuccess(List<T> List);

    //Todos lo repositorio lista contemplan los casos de delete y undo
    void onDeleteSuccess(String message);
    void onUndoSuccess(String message);
    void onUpdateSuccess(String message);
}
