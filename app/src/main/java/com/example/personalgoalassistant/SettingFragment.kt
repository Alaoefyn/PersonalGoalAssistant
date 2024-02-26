package com.example.personalgoalassistant

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment

class SettingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_setting, container, false)
        val sharedPref = activity?.getPreferences(MODE_PRIVATE)?.edit()

        if (activity?.getPreferences(MODE_PRIVATE)?.getInt("DarskModeValue", 0) == 1) {
            view.findViewById<Switch>(R.id.darkmode).isChecked = true
        }

        view.findViewById<Switch>(R.id.darkmode).setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                true -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    sharedPref?.putInt("DarskModeValue", 1)
                    sharedPref?.apply()
                }
                false -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    sharedPref?.putInt("DarskModeValue", 0)
                    sharedPref?.apply()
                }
            }
        }

        return view
    }
}
