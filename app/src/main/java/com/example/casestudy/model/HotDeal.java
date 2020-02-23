package com.example.casestudy.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;
import java.util.UUID;

public class HotDeal extends BaseObservable {
    private String id = UUID.randomUUID().toString();
    private String title;
    private Date expirationDate;

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
        notifyPropertyChanged(BR.expirationDate);

    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HotDeal)) return false;
        HotDeal hotDeal = (HotDeal) o;
        return getId().equals(hotDeal.getId()) &&
                getTitle().equals(hotDeal.getTitle()) &&
                getExpirationDate().equals(hotDeal.getExpirationDate());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getExpirationDate())
                .append(getId())
                .append(getTitle())
                .toHashCode();

    }


}
