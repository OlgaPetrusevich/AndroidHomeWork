package com.gmail.petrusevich.volha.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val onNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener by lazy {
        BottomNavigationView.OnNavigationItemSelectedListener(){
            when(it.itemId){
                R.id.viewNavigationGym -> loadFragment(WorkoutListFragment.gteInstance())
                R.id.viewNavigationHistory -> loadFragment(HistoryListFragment.gteInstance())
                R.id.viewNavigationProfile -> loadFragment(UserFragment.gteInstance())
                else -> false
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewButtonNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        viewButtonNavigation.selectedItemId = R.id.viewNavigationGym
    }

    private fun loadFragment(fragment: Fragment): Boolean{
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()
        return true
    }



}
