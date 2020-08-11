package com.example.android.databinding.basicsample.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.android.databinding.basicsample.R;
import com.example.android.databinding.basicsample.data.SimpleViewModel;
import com.example.android.databinding.basicsample.databinding.PlainActivityBinding;

public class PlainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimpleViewModel viewModel =
                new ViewModelProvider(this)
                        .get(SimpleViewModel.class);

        PlainActivityBinding binding =
                DataBindingUtil.setContentView(this, R.layout.plain_activity);

        binding.setLifecycleOwner(this);
        binding.setViewmodel(viewModel);
    }
}
