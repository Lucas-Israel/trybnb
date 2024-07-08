package com.betrybe.trybnb.ui.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.betrybe.trybnb.R
import com.betrybe.trybnb.ui.views.fragments.CreateReservationFragment
import com.betrybe.trybnb.ui.views.fragments.ProfileFragment
import com.betrybe.trybnb.ui.views.fragments.ReservationFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val mBottomMenu: BottomNavigationView by lazy { findViewById(R.id.navigation_bottom_view) }
    private val reservationFragment = ReservationFragment()
    private val createReservationFragment = CreateReservationFragment()
    private val profileFragment = ProfileFragment()

    // init
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBottomMenu.setOnItemReselectedListener {
            when (it.itemId) {

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
        }
    }
}
