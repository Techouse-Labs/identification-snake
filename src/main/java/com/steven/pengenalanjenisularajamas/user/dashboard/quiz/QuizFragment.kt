package com.steven.pengenalanjenisularajamas.user.dashboard.quiz

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.steven.pengenalanjenisularajamas.R
import com.steven.pengenalanjenisularajamas.admin.login.LoginActivity
import com.steven.pengenalanjenisularajamas.databinding.FragmentQuizBinding
import com.steven.pengenalanjenisularajamas.user.quiz.QuizActivity

class QuizFragment : Fragment() {
    private lateinit var binding: FragmentQuizBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivLogin.setOnClickListener {
            Intent(requireContext(), LoginActivity::class.java).also {
                startActivity(it)
            }
        }
        binding.btnStart.setOnClickListener {
            Intent(requireContext(), QuizActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        val pref = requireActivity().getSharedPreferences("main", MODE_PRIVATE)
        binding.tvBest.text = "Skor terbaik: ${pref.getInt("skor", 0)}"
        super.onResume()
    }
}