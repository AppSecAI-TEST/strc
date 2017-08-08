package com.sweatshop.storycal.presentationlayer.profile_picture.popup;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by arTeam on 03/08/2017.
 */

public class BaseDialog {
    public static final int DEFAULT_ITEM_COUNT_MODE_CURVED = 7;
    public static final int DEFAULT_ITEM_COUNT_MODE_NORMAL = 5;

    @Nullable
    private boolean isDisplaying;
    @ColorInt
    protected Integer backgroundColor = null;

    @Nullable
    @ColorInt
    protected Integer mainColor = Color.BLACK;

    @Nullable
    @ColorInt
    protected Integer titleTextColor = null;

    protected boolean okClicked = false;


    public void display() {
        this.isDisplaying = true;
    }

    public void close() {
        this.isDisplaying = false;
    }

    public boolean isDisplaying() {
        return isDisplaying;
    }

    public void setBackgroundColor(@ColorInt Integer backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setMainColor(@ColorInt Integer mainColor) {
        this.mainColor = mainColor;
    }

    public void setTitleTextColor(@NonNull @ColorInt int titleTextColor) {
        this.titleTextColor = titleTextColor;
    }

    protected void onClose() {
        this.isDisplaying = false;
    }
}
