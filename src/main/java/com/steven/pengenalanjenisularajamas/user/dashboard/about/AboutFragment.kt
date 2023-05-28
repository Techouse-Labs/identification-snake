package com.steven.pengenalanjenisularajamas.user.dashboard.about

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.steven.pengenalanjenisularajamas.BuildConfig
import com.steven.pengenalanjenisularajamas.R
import com.steven.pengenalanjenisularajamas.admin.login.LoginActivity
import com.steven.pengenalanjenisularajamas.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val versionName: String = BuildConfig.VERSION_NAME
        binding.apply {
            binding.tvVersion.text = "v${versionName}"
            ivLogin.setOnClickListener {
                Intent(requireContext(), LoginActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }
}