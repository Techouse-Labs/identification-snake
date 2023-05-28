package com.steven.pengenalanjenisularajamas.user.dashboard.fact

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.steven.pengenalanjenisularajamas.R
import com.steven.pengenalanjenisularajamas.admin.SnakeItemAdapter
import com.steven.pengenalanjenisularajamas.databinding.FragmentFactBinding
import com.steven.pengenalanjenisularajamas.databinding.FragmentHomeBinding
import com.steven.pengenalanjenisularajamas.admin.login.LoginActivity
import com.steven.pengenalanjenisularajamas.user.dashboard.DashboardViewModel
import com.steven.pengenalanjenisularajamas.user.detail.DetailActivity
import com.steven.pengenalanjenisularajamas.user.factDetail.FactDetailActivity

class FactFragment : Fragment() {
    private lateinit var binding: FragmentFactBinding
    private lateinit var adapter: SnakeItemAdapter

    private val viewModel by viewModels<DashboardViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SnakeItemAdapter(requireContext()) {
            Intent(requireContext(), FactDetailActivity::class.java).also { i ->
                i.putExtra("data", it)
                startActivity(i)
            }
        }

        binding.apply {
            ivLogin.setOnClickListener {
                Intent(requireContext(), LoginActivity::class.java).also {
                    startActivity(it)
                }
            }
            rvType.apply {
                this.adapter = this@FactFragment.adapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

        viewModel.apply {
            onLoading.observe(viewLifecycleOwner) {
                if(it) binding.pbLoading.visibility = View.VISIBLE
                else binding.pbLoading.visibility = View.GONE
            }
            onError.observe(viewLifecycleOwner) {
                if(it.isNotEmpty()) {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }
            types.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

        viewModel.getFacts()
    }
}