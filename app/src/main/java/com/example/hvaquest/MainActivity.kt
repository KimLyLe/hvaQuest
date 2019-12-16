package com.example.hvaquest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.hvaquest.ViewModels.QuestViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: QuestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(QuestViewModel::class.java)
        initNavigation()
    }

    private fun initNavigation() {
        // The NavController
        val navController = findNavController(R.id.navHostFragment)

        // Connect the navHostFragment with the ActionBar.
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        if(onBackPressedDispatcher.hasEnabledCallbacks()) {
            onBackPressedDispatcher.onBackPressed()
        } else {
            onBackPressed()
        }
        return true
    }
}
