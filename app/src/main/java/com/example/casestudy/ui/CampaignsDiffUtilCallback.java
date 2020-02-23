package com.example.casestudy.ui;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.casestudy.model.Banner;
import com.example.casestudy.model.HotDeal;

public class CampaignsDiffUtilCallback extends DiffUtil.ItemCallback {


    @Override
    public boolean areItemsTheSame(@NonNull Object oldItem, @NonNull Object newItem) {
        if (oldItem instanceof Banner && newItem instanceof Banner)
            return ((Banner) oldItem).getId().equals(((Banner) newItem).getId());

        else if (oldItem instanceof HotDeal && newItem instanceof HotDeal)
            return ((HotDeal) oldItem).getId().equals(((HotDeal) newItem).getId());

        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Object oldItem, @NonNull Object newItem) {
        if (oldItem instanceof Banner && newItem instanceof Banner)
            return ((Banner) newItem).equals(((Banner) oldItem));

        else if (oldItem instanceof HotDeal && newItem instanceof HotDeal)
            return ((HotDeal) newItem).equals(((HotDeal) oldItem));

        return false;
    }
}
