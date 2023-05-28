package com.steven.pengenalanjenisularajamas.user.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.steven.pengenalanjenisularajamas.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getSerializableExtra("data") as Map<String, Any>
        binding.apply {
            textView2.text = data["name"] as String
            textView3.text = data["detail"] as String

            Glide.with(this@DetailActivity).load(data["imageUrl"]).into(imageView)
        }
    }
}