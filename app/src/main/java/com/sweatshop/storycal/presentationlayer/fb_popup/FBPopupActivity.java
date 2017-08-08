package com.sweatshop.storycal.presentationlayer.fb_popup;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.databinding.ActivityFbPopupBinding;

public class FBPopupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        ActivityFbPopupBinding activityFbPopupBinding = DataBindingUtil.setContentView(this, R.layout.activity_fb_popup);
        activityFbPopupBinding.setFBPopupViewModel(new FBPopupViewModel(this));
    }
}