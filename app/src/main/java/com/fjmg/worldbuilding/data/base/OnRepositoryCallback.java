package com.fjmg.worldbuilding.data.base;

public interface OnRepositoryCallback {
    void onSuccess(String UID);
    void onFailure(String message);
}
