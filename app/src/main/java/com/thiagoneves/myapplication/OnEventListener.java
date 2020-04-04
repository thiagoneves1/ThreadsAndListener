package com.thiagoneves.myapplication;

public interface OnEventListener<T> {
    void onSuccess(T object);
    void onFailure(Exception e);
}