package com.steven.pengenalanjenisularajamas.admin.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.steven.pengenalanjenisularajamas.admin.snakes.SnakesActivity
import com.steven.pengenalanjenisularajamas.user.dashboard.DashboardActivity
import com.steven.pengenalanjenisularajamas.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnLogin.setOnClickListener {
                val username = etUsername.editText?.text.toString()
                val password = etPassword.editText?.text.toString()

                if(username == "admin101" && password == "admin101") {
                    Intent(this@LoginActivity, SnakesActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Username / Password salah!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}