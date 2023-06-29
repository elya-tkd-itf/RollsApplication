package com.dasonick.rollsapplication.ui.rolls

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.dasonick.rollsapplication.R
import com.dasonick.rollsapplication.base.Roll
import com.squareup.picasso.Picasso

class RollsRecyclerAdapter(
    private val rolls: List<Roll>,
    private val navController: NavController
) :
    RecyclerView.Adapter<RollsRecyclerAdapter.MyViewHolder>() {

    private lateinit var context: Context

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.roll_title)
        val imageView: ImageView = itemView.findViewById(R.id.roll_image)
        val layout: View = itemView.findViewById(R.id.item_layout)
    }

    override fun getItemCount() = rolls.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item, parent, false)
        context = parent.context
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titleTextView.text = rolls[position].title
        holder.layout.setOnClickListener {
            //navController.navigate(R.id.action_categoriesFragment_to_beautyServicesFragment)
        }
        Picasso.with(context)
            .load(rolls[position].imageUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            //.error(R.drawable.ic_baseline_home_24)
            .into(holder.imageView);
    }
}