/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.databinding.basicsample.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

/**
 * A simple VM for [com.example.android.databinding.basicsample.ui.PlainOldActivity].
 */
class SimpleViewModel : ViewModel() {
    private val TAG = SimpleViewModel::class.java.simpleName

    private val _name = MutableLiveData("Mahatta")
    private val _lastname = MutableLiveData("Maulana")
    private val _likes = MutableLiveData(0)

    val name: LiveData<String> = _name
    val lastname: LiveData<String> = _lastname
    val likes: LiveData<Int> = _likes

    /**
     * Increments the number of likes.
     */
    fun onLike() {
        _likes.value = (_likes.value ?: 0) + 1
    }

    /**
     * Returns popularity in buckets: [Popularity.NORMAL], [Popularity.POPULAR] or [Popularity.STAR]
     */
    val popularity: LiveData<Popularity> = Transformations.map(_likes) { like ->
        when {
            like > 9 -> Popularity.STAR
            like > 4 -> Popularity.POPULAR
            else -> Popularity.NORMAL
        }
    }
}

enum class Popularity {
    NORMAL,
    POPULAR,
    STAR
}
