package com.example.casestudy.ui;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.casestudy.R;
import com.example.casestudy.databinding.RvBannerItemBinding;
import com.example.casestudy.databinding.RvHotdealItemBinding;
import com.example.casestudy.model.Banner;
import com.example.casestudy.model.HotDeal;

import java.util.Date;
import java.util.concurrent.TimeUnit;


public class CampaignsAdapter extends PagedListAdapter<Object, RecyclerView.ViewHolder> {

    //type of item
    private static final int BANNER = 0;
    private static final int HOT_DEAL = 1;

    public CampaignsAdapter() {
        super(new CampaignsDiffUtilCallback());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case BANNER:
                viewHolder = new BannerViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.rv_banner_item, parent, false));
                break;
            case HOT_DEAL:
                viewHolder = new HotDealViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.rv_hotdeal_item, parent, false));
                break;
        }
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case BANNER:
                ((BannerViewHolder) holder).bannerItemBinding.setBanner((Banner) getItem(position));
                break;
            case HOT_DEAL:
                ((HotDealViewHolder) holder).rvHotdealItemBinding.setHotDeal(((HotDeal) getItem(position)));
                ((HotDealViewHolder) holder).startCountDown(((HotDeal) getItem(position)).getExpirationDate());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position) instanceof Banner ? BANNER : HOT_DEAL;
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        private RvBannerItemBinding bannerItemBinding;


        BannerViewHolder(@NonNull RvBannerItemBinding bannerItemBinding) {
            super(bannerItemBinding.getRoot());
            this.bannerItemBinding = bannerItemBinding;
        }
    }

    public class HotDealViewHolder extends RecyclerView.ViewHolder {
        private RvHotdealItemBinding rvHotdealItemBinding;
        private CountDownTimer timer = null;


        HotDealViewHolder(@NonNull RvHotdealItemBinding rvHotdealItemBinding) {
            super(rvHotdealItemBinding.getRoot());
            this.rvHotdealItemBinding = rvHotdealItemBinding;
        }

        void startCountDown(Date date) {
            if (this.timer != null) {
                this.timer.cancel();
            }
            long currentTime = new Date().getTime();
            if (date.getTime() > currentTime) {
                rvHotdealItemBinding.tvCountDown.setTextColor(Color.BLACK);
                this.timer = new CountDownTimer(date.getTime(), 1000) {
                    String countDownStr = "";

                    public void onTick(long millisUntilFinished) {
                        long remainingMills = millisUntilFinished - currentTime;
                        long seconds = TimeUnit.MILLISECONDS.toSeconds(remainingMills) % 60;
                        long minutes = TimeUnit.MILLISECONDS.toMinutes(remainingMills) % 60;
                        long hours = TimeUnit.MILLISECONDS.toHours(remainingMills) % 24;
                        long days = TimeUnit.MILLISECONDS.toDays(remainingMills);

                        if (days > 0)
                            countDownStr = days + " Days, ";

                        if (hours > 0)
                            countDownStr = countDownStr + hours + " Hours, ";

                        if (minutes > 0)
                            countDownStr = countDownStr + minutes + " Minutes, ";

                        countDownStr = countDownStr + seconds + " Seconds Remaining";

                        rvHotdealItemBinding.tvCountDown.setText(countDownStr);
                    }

                    public void onFinish() {
                        rvHotdealItemBinding.tvCountDown.setTextColor(Color.RED);
                        rvHotdealItemBinding.tvCountDown.setText(itemView.getContext().getString(R.string.EXPIRED));
                    }
                }.start();
            } else {
                rvHotdealItemBinding.tvCountDown.setTextColor(Color.RED);
                rvHotdealItemBinding.tvCountDown.setText(itemView.getContext().getString(R.string.EXPIRED));
            }
        }
    }
}
