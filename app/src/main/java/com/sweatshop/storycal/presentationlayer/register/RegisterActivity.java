package com.sweatshop.storycal.presentationlayer.register;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sweatshop.storycal.R;
import com.sweatshop.storycal.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        ActivityRegisterBinding registerBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        registerBinding.setRegisterViewModel(new RegisterViewModel(this));
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
