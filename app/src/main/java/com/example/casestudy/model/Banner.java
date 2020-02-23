package com.example.casestudy.model;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.DiffUtil;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.UUID;

public class Banner extends BaseObservable {
    private String id = UUID.randomUUID().toString();
    private Image image;

    @Bindable
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        notifyPropertyChanged(BR.image);
    }

    public static final DiffUtil.ItemCallback<Banner> CALLBACK = new DiffUtil.ItemCallback<Banner>() {
        @Override
        public boolean areItemsTheSame(@NonNull Banner oldItem, @NonNull Banner newItem) {
            return oldItem.id.equals(newItem.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Banner oldItem, @NonNull Banner newItem) {
            return oldItem.equals(newItem);
        }
    };

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Banner)) return false;
        Banner banner = (Banner) o;
        return getId().equals(banner.getId()) &&
                getImage().equals(banner.getImage());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getImage())
                .toHashCode();
    }
}

