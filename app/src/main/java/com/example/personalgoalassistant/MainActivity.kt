package com.example.personalgoalassistant

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.personalgoalassistant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AddTaskLayout.OnFragmentInteractionListener {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.task -> replaceFragment(TaskFragment(""))
                R.id.calender -> replaceFragment(TaskFragment("Calender"))
                R.id.notifications -> replaceFragment(NotificationFragment())
                R.id.settings -> replaceFragment(SettingFragment())
                else -> {
                }
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        if(getPreferences(MODE_PRIVATE)?.getInt("DarskModeValue",0) == 1)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

    override fun onCloseFragment() {
        val fragment = supportFragmentManager.findFragmentById(R.id.frameLayout)
        if (fragment is AddTaskLayout) {
            supportFragmentManager.beginTransaction().remove(fragment).commit()
        }
    }
}
