package com.utsman.binarroom.features.adapter

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.utsman.binarroom.model.User
import com.utsman.binarroom.databinding.ItemUserBinding
import com.utsman.binarroom.features.activity.UpdateActivity

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(user: User) = ItemUserBinding.bind(itemView).run {
        itemTvName.text = user.name
        itemTvUmur.text = user.age.toString()

        btnUpdate.setOnClickListener {
            val context = itemView.context
            val intent = Intent(context, UpdateActivity::class.java)
            intent.putExtra("user_id", user.id)
            context.startActivity(intent)
        }


    }
}