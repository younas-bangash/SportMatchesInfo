package com.sport.matchesinfo.view

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sport.matchesinfo.R
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity


/**
 * Activity for Hosting the Fragment
 */
class FragmentContainerActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}