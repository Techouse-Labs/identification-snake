package com.steven.pengenalanjenisularajamas.admin.addSnake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.esafirm.imagepicker.model.Image
import com.steven.pengenalanjenisularajamas.databinding.ActivityAddSnakeBinding

class AddSnakeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddSnakeBinding

    private val viewModel by viewModels<AddSnakeViewModel>()
    private val launcher = registerImagePicker {
        if(it.isNotEmpty()) {
            pickedImage = it.first()
            Glide.with(this).load(it.first().uri).into(binding.btnAddImage)
        }
    }

    var pickedImage: Image? = null
    var isUnique = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddSnakeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            swUnique.setOnCheckedChangeListener { _, b ->
                isUnique = b

                if(b) {
                    etUnique.visibility = View.VISIBLE
                } else {
                    etUnique.visibility = View.GONE
                }
            }

            btnAddImage.setOnClickListener { pickImage() }
            btnAdd.setOnClickListener {
                val name = etName.editText?.text.toString()
                val detail = etDetail.editText?.text.toString()
                var unique = etUnique.editText?.text.toString()

                if(!isUnique) unique = ""
                if(name.isNotEmpty() && detail.isNotEmpty() && pickedImage != null) {
                    viewModel.saveData(name, detail, unique, pickedImage!!)
                }
            }
        }

        viewModel.onLoading.observe(this) {
            binding.btnAdd.isEnabled = !it
            binding.btnAddImage.isEnabled = !it
        }
        viewModel.onError.observe(this) {
            if(it.isNotEmpty()) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.isDone.observe(this) {
            if(it) {
                finish()
                Toast.makeText(this, "Data telah ditambahkan!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun pickImage() {
        val config = ImagePickerConfig {
            mode = ImagePickerMode.SINGLE
            language = "in"
            returnMode = ReturnMode.ALL
            imageTitle = "Tap to select" // image selection title
            doneButtonText = "DONE" // done button text
            limit = 10 // max images can be selected (99 by default)
            isShowCamera = true // show camera or not (true by default)
        }

        launcher.launch(config)
    }
}