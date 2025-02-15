package com.betrybe.trybnb.ui.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.betrybe.trybnb.R
import com.betrybe.trybnb.ui.views.fragments.CreateReservationFragment
import com.betrybe.trybnb.ui.views.fragments.ProfileFragment
import com.betrybe.trybnb.ui.views.fragments.ReservationFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mBottomMenu: BottomNavigationView by lazy { findViewById(R.id.navigation_bottom_view) }
    private val reservationFragment = ReservationFragment()
    private val createReservationFragment = CreateReservationFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBottomMenu.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.reservation_menu_item -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment_container, reservationFragment)
                        .commit()
                }

                R.id.create_reservation_menu_item -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment_container, createReservationFragment)
                        .commit()
                }

                R.id.profile_menu_tem -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment_container, profileFragment)
                        .commit()
                }
            }
            true
        }
    }
}
