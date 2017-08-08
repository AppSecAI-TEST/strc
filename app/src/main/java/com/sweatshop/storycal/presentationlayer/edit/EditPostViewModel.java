package com.sweatshop.storycal.presentationlayer.edit;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

import java.util.Date;

/**
 * Created by arTeam on 01/08/2017.
 */

public class EditPostViewModel extends BaseObservable {
    private Context context;
    private Date date;

    public EditPostViewModel(Context context) {
        this.context = context;
    }

    @Bindable
    public Date getDate() { return this.date; }

    public void setDate(Date date) {
        this.date = date;
        notifyPropertyChanged(BR.date);
    }
}
