package com.steven.pengenalanjenisularajamas.admin.snakes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.steven.pengenalanjenisularajamas.admin.SnakeItemAdapter
import com.steven.pengenalanjenisularajamas.admin.addSnake.AddSnakeActivity
import com.steven.pengenalanjenisularajamas.admin.updateSnake.UpdateSnakeActivity
import com.steven.pengenalanjenisularajamas.databinding.ActivitySnakesBinding

class SnakesActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySnakesBinding
    private lateinit var adapter: SnakeItemAdapter

    private val viewModel by viewModels<SnakesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = SnakeItemAdapter(this) {
            Intent(this, UpdateSnakeActivity::class.java).also { i ->
                val bundle = Bundle()
                bundle.putSerializable("data", it)

                i.putExtra("bundle", bundle)
                startActivity(i)
            }
        }

        binding = ActivitySnakesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            rvType.apply {
                adapter = this@SnakesActivity.adapter
                layoutManager = LinearLayoutManager(this@SnakesActivity,
                    LinearLayoutManager.HORIZONTAL, false)
            }
            fabAdd.setOnClickListener {
                Intent(this@SnakesActivity, AddSnakeActivity::class.java).also {
                   startActivity(it)
                }
            }
        }

        viewModel.apply {
            onError.observe(this@SnakesActivity) {
                if(it.isNotEmpty()) Toast.makeText(this@SnakesActivity, it, Toast.LENGTH_SHORT).show()
            }
            snakes.observe(this@SnakesActivity) {
                adapter.submitList(it)
            }
        }


        viewModel.getSnakes()
    }
}