package com.steven.pengenalanjenisularajamas.admin

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.steven.pengenalanjenisularajamas.databinding.ListItemBinding

class SnakeItemAdapter(
    private val context: Context,
    private val onClick: (HashMap<String, Any>) -> Unit
): Adapter<SnakeItemAdapter.SnakeItemViewHolder>() {
    private val types = arrayListOf<Map<String, Any>>()

    inner class SnakeItemViewHolder(
        private val binding: ListItemBinding
    ): ViewHolder(binding.root) {

        fun bind(data: Map<String, Any>) {
            binding.apply {
                tvSnake.text = data["name"] as String
                tvDesc.text = data["detail"] as String
                Glide.with(context).load(data["imageUrl"] as String).into(ivSnake)

                itemView.setOnClickListener { onClick(data as HashMap<String, Any>) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnakeItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)

        return SnakeItemViewHolder(binding)
    }

    override fun getItemCount(): Int = types.size

    override fun onBindViewHolder(holder: SnakeItemViewHolder, position: Int) =
        holder.bind(types[position])

    fun submitList(data: List<Map<String, Any>>) {
        types.clear()
        types.addAll(data)
        notifyDataSetChanged()
    }
}