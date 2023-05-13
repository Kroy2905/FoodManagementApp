package com.foodApp.managementapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.foodApp.managementapp.R
import com.foodApp.managementapp.base.BaseActivity
import com.foodApp.managementapp.databinding.ActivityRestaurantHomeScreenBinding
import com.foodApp.managementapp.viewmodels.RestaurantHomeViewModel

class RestaurantHomeScreen : BaseActivity<ActivityRestaurantHomeScreenBinding, RestaurantHomeViewModel>(
    RestaurantHomeViewModel::class.java,
    { inflater -> ActivityRestaurantHomeScreenBinding.inflate(inflater) }
){

    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.showProgressBar()



     // setSupportActionBar(binding.appBarRestaurantHomeScreen.toolbar)

        binding.appBarRestaurantHomeScreen.report.setOnClickListener { view ->
            Snackbar.make(view, "Report services to be added later", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_restaurant_home_screen)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,R.id.nav_orders,R.id.nav_menu,R.id.nav_additem
            ), drawerLayout
        )
       // setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.restaurant_home_screen, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_restaurant_home_screen)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
           // handle on options item selected here
        }
        return super.onOptionsItemSelected(item)
    }


    override fun setupViews() {


    }
}