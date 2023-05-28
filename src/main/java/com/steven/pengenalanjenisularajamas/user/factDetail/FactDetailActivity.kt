package com.steven.pengenalanjenisularajamas.user.factDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.steven.pengenalanjenisularajamas.databinding.ActivityFactBinding

class FactDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getSerializableExtra("data") as Map<String, Any>
        binding.apply {
            textView3.text = data["unique"] as String
            Glide.with(this@FactDetailActivity).load(data["imageUrl"]).into(imageView)
        }
    }
}