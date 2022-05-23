package com.utsman.binarroom

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.utsman.binarroom.databinding.ItemUserBinding

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