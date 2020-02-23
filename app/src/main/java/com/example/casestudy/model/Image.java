package com.example.casestudy.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class Image extends BaseObservable {
    private int width;
    private int height;
    private String url;

    @Bindable
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        notifyPropertyChanged(BR.width);
    }

    @Bindable
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        notifyPropertyChanged(BR.height);
    }

    @Bindable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.url);
    }
}
