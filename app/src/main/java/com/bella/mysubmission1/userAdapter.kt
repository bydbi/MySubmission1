package com.bella.mysubmission1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bella.mysubmission1.databinding.ItemUserBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class userAdapter : RecyclerView.Adapter<userAdapter.userViewHolder>() {

    private val list = ArrayList<user>()

    fun setList(users: ArrayList<user>){
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    inner class userViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user: user){
            binding.apply {
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(imgUser)
                tvUname.text = user.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return userViewHolder((view))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: userViewHolder, position: Int) {
        holder.bind(list[position])
    }
}