package com.steven.pengenalanjenisularajamas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.steven.pengenalanjenisularajamas.user.dashboard.DashboardActivity
import com.steven.pengenalanjenisularajamas.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activityScope.launch {
            delay(1000)

            Intent(this@MainActivity, DashboardActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}