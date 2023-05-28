package com.steven.pengenalanjenisularajamas.admin.updateSnake

import android.net.Uri
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
import com.steven.pengenalanjenisularajamas.databinding.ActivityUpdateSnakeBinding

class UpdateSnakeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateSnakeBinding

    private val viewModel by viewModels<UpdateSnakeViewModel>()
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

        binding = ActivityUpdateSnakeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.getBundleExtra("bundle")
        val data = bundle?.getSerializable("data") as Map<String, Any>

        binding.apply {
            if((data["unique"] as String).isNotEmpty()) {
                isUnique = true
                binding.swUnique.isChecked = true
                binding.etUnique.visibility = View.VISIBLE
            }

            etName.editText?.setText(data["name"] as String)
            etDetail.editText?.setText(data["detail"] as String)
            etUnique.editText?.setText(data["unique"] as String)
            Glide.with(this@UpdateSnakeActivity).load(Uri.parse(data["imageUrl"] as String))
                .into(binding.btnAddImage)

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
                if(name.isNotEmpty() && detail.isNotEmpty()) {
                    viewModel.saveData(data["id"] as String, name, detail, unique, pickedImage)
                }
            }
            btnDelete.setOnClickListener {
                viewModel.delete(data["id"] as String)
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
                Toast.makeText(this, "Sukses!", Toast.LENGTH_SHORT).show()
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