package com.sport.matchesinfo.view

import android.os.Bundle
import com.sport.matchesinfo.R
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