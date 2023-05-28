package com.steven.pengenalanjenisularajamas.user.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.steven.pengenalanjenisularajamas.R
import com.steven.pengenalanjenisularajamas.admin.SnakeItemAdapter
import com.steven.pengenalanjenisularajamas.admin.snakes.SnakesActivity
import com.steven.pengenalanjenisularajamas.databinding.ActivityDashboardBinding
import com.steven.pengenalanjenisularajamas.databinding.ActivityUserDashboardBinding
import com.steven.pengenalanjenisularajamas.admin.login.LoginActivity
import com.steven.pengenalanjenisularajamas.user.dashboard.about.AboutFragment
import com.steven.pengenalanjenisularajamas.user.dashboard.fact.FactFragment
import com.steven.pengenalanjenisularajamas.user.dashboard.home.HomeFragment
import com.steven.pengenalanjenisularajamas.user.dashboard.quiz.QuizFragment

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moveFragment(HomeFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.menu_home -> {
                    moveFragment(HomeFragment())
                    true
                }
                R.id.menu_fact -> {
                    moveFragment(FactFragment())
                    true
                }
                R.id.menu_quiz -> {
                    moveFragment(QuizFragment())
                    true
                }
                else -> {
                    moveFragment(AboutFragment())
                    true
                }
            }
        }
    }

    private fun moveFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}