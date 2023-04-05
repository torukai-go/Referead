package com.toru.referead

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.toru.referead.ui.main.MainFragment
import androidx.navigation.Navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(this, R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.nav_host_fragment, MainFragment.newInstance())
//                .commitNow()
//        }
    }
}