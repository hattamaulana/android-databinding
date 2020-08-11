package com.example.android.databinding.basicsample.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.databinding.BindingAdapter;

import com.example.android.databinding.basicsample.R;
import com.example.android.databinding.basicsample.data.Popularity;

public class BindingAdapters {

    private static final String TAG = BindingAdapter.class.getSimpleName();

    @BindingAdapter("android:text")
    public static void setText(TextView textView, CharSequence text) {
        textView.setText(text);
    }

    @BindingAdapter("android:bacground")
    public static void setBackground(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    /**
     * A Binding Adapter that is called whenever the value of the attribute `app:popularityIcon`
     * changes. Receives a popularity level that determines the icon and tint color to use.
     */
    @BindingAdapter("app:popularityIcon")
    public static void popularityIcon(ImageView view, String popularity) {
        Log.i(TAG,"popularityIcon: popularity="+ popularity);
        int color = getAssociatedColor(popularity, view.getContext());
        Drawable drawable = getDrawablePopularity(popularity, view.getContext());

        ImageViewCompat.setImageTintList(view, ColorStateList.valueOf(color));
        view.setImageDrawable(drawable);
    }



    /**
     * A Binding Adapter that is called whenever the value of the attribute `android:progressTint`
     * changes. Depending on the value it determines the color of the progress bar.
     */
    @BindingAdapter("app:progressTint")
    public static void tintPopularity(ProgressBar view, String popularity) {
        int color = getAssociatedColor(popularity, view.getContext());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setProgressTintList(ColorStateList.valueOf(color));
        }
    }

    /**
     *  Sets the value of the progress bar so that 5 likes will fill it up.
     *
     *  Showcases Binding Adapters with multiple attributes. Note that this adapter is called
     *  whenever any of the attribute changes.
     */
    @BindingAdapter(value = {"app:progressScaled", "android:max"}, requireAll = true)
    public static void setProgress(ProgressBar progressBar, int likes, int max) {
        progressBar.setProgress(likes);
    }

    /**
     * Unused Binding Adapter to replace the Binding Converter that hides a view if the number
     * of likes is zero.
     */
    @BindingAdapter("app:hideIfZero")
    public static void hideIfZero(View view, Integer number) {
        view.setVisibility(number == 0 ? View.GONE : View.VISIBLE);
    }

    private static Integer getAssociatedColor(String popularity, Context context) {
        switch (popularity) {
            case Popularity.POPULAR:
                return ContextCompat.getColor(context, R.color.popular);

            case Popularity.STAR:
                return ContextCompat.getColor(context, R.color.star);

            default:
                return ContextCompat.getColor(context, android.R.color.darker_gray);
        }
    }

    private static Drawable getDrawablePopularity(String popularity, Context context) {
        switch (popularity) {
            case Popularity.NORMAL:
                return ContextCompat.getDrawable(context, R.drawable.ic_person_black_96dp);

            case Popularity.POPULAR:
                return ContextCompat.getDrawable(context, R.drawable.ic_whatshot_black_96dp);

            case Popularity.STAR:
                return ContextCompat.getDrawable(context, R.drawable.ic_whatshot_black_96dp);

            default:
                return null;
        }
    }
}
