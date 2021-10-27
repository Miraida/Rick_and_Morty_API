package com.geek.rick_and_morty_api.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.geek.rick_and_morty_api.R
import com.geek.rick_and_morty_api.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        val navView: BottomNavigationView = binding.navView

        val navHos =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHos.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            run {
                if (destination.id != R.id.navigation_character &&
                    destination.id != R.id.navigation_episode &&
                    destination.id != R.id.navigation_location &&
                    destination.id != R.id.navigation_search
                )
                    navView.visibility = View.GONE
                else navView.visibility = View.VISIBLE
            }
        }

        navView.setupWithNavController(navController)
    }
}