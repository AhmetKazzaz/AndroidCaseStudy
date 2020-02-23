package com.example.casestudy.util;

import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class BindingAdapters {

    /**
     * setting url by picasso through a bound parameter from the model.
     *
     * @param view:     ImageView
     * @param imageUrl: url param from Image model.
     */
    @BindingAdapter("android:setImageSource")
    public static void setImageResource(ImageView view, String imageUrl) {
        Picasso.get().load(imageUrl).into(view);
    }

    /**
     * Dynamically bind the height of the imageView from the model.
     * to ensure the image is flexible and controllable from the service
     *
     * @param view:   the imageView
     * @param height: height bound by model.
     */
    @BindingAdapter("bind:layout_height")
    public static void setLayoutHeight(ImageView view, int height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = height;
        view.setLayoutParams(layoutParams);
    }

    /**
     * Dynamically bind the width of the imageView from the model.
     * to ensure the image is flexible and controllable from the service
     *
     * @param view:  the imageView
     * @param width: width bound by model.
     */
    @BindingAdapter("bind:layout_width")
    public static void setLayoutWidth(ImageView view, int width) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = width;
        view.setLayoutParams(layoutParams);
    }

}
