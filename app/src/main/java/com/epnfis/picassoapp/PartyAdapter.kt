package com.epnfis.picassoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class PartyAdapter(
    private val context: Context,
    private val parties: IntArray,
    private val layout: Int
) :
    RecyclerView.Adapter<PartyAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(context).inflate(layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        Picasso.get().load(parties[position]).fit().placeholder(R.drawable.spinner)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return parties.size
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var image: ImageView

        init {
            image =
                itemView.findViewById<View>(R.id.imageViewLayout) as ImageView
        }
    }

}