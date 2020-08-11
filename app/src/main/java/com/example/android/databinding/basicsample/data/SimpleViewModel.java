package com.example.android.databinding.basicsample.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class SimpleViewModel extends ViewModel {

    private final String TAG = SimpleViewModel.class.getSimpleName();

    private MutableLiveData<String> mFirstName = new MutableLiveData<>("Mahatta");
    private MutableLiveData<String> mLastName = new MutableLiveData<>("Maulana");
    private MutableLiveData<Integer> mLikes = new MutableLiveData<>(0);

    public LiveData<String> firstName = mFirstName;
    public LiveData<String> lastName = mLastName;
    public LiveData<Integer> likes = mLikes;

    public LiveData<String> getPopularity() {
        return Transformations.map(mLikes, like -> {
            Log.i(TAG, "getPopularity: popularity="+ like);
            if (like > 75) {
                return Popularity.STAR;
            } else if (like > 25) {
                return Popularity.POPULAR;
            } else {
                return Popularity.NORMAL;
            }
        });
    }

    public void onLike(int max) {
        int value = mLikes.getValue() == null ? 0 : mLikes.getValue();

        Log.i(TAG, "onLike: max="+ max);

        if (value < max) {
            mLikes.setValue(value + 1);
        }
    }
}
