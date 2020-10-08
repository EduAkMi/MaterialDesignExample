package com.kaltur.materialdesignexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val fragmentSearch = FragmentSearch()
    val fragmentFavorite = FragmentFavorite()
    val fragmentProfile = FragmentProfile()
    var active = Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fm = supportFragmentManager
        active = fragmentSearch
        fm.beginTransaction().add(R.id.fragment_container, fragmentSearch, null).commit()

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_busca -> {
                    val fragmentSearch2 = FragmentSearch()
                    fm.beginTransaction().hide(active)
                        .add(R.id.fragment_container, fragmentSearch2, null).commit()
//                    fm.beginTransaction().hide(active).show(fragmentSearch).commit()
                    active = fragmentSearch2
                }
                R.id.bottom_favoritos -> {
                    if (fm.findFragmentByTag("2") == null)
                        fm.beginTransaction().hide(active)
                            .add(R.id.fragment_container, fragmentFavorite, "2").commit()
                    else
                        fm.beginTransaction().hide(active)
                            .show(fragmentFavorite).commit()
                    active = fragmentFavorite
                }
                R.id.bottom_perfil -> {
                    if (fm.findFragmentByTag("3") == null)
                        fm.beginTransaction().hide(active)
                            .add(R.id.fragment_container, fragmentProfile, "3").commit()
                    else
                        fm.beginTransaction().hide(active)
                            .show(fragmentProfile).commit()
                    active = fragmentProfile
                }
            }
            true
        }
    }
}