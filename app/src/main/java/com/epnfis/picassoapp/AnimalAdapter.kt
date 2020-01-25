package com.epnfis.picassoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class AnimalAdapter(
    private val context: Context,
    private val animals: Array<String>,
    private val layout: Int
) :
    RecyclerView.Adapter<AnimalAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnimalAdapter.ViewHolder {
        val v = LayoutInflater.from(context).inflate(layout, parent, false)
        return AnimalAdapter.ViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: AnimalAdapter.ViewHolder,
        position: Int
    ) {
        Picasso.get().load(animals[position]).fit().placeholder(R.drawable.spinner)
            .into(holder.image, object : Callback {
                override fun onSuccess() {
                    //holder.image.setVisibility(View.INVISIBLE);
                }

                override fun onError(e: Exception) {
                    Toast.makeText(context, "Error al cargar alguna imagen!", Toast.LENGTH_SHORT).show()
                }
            })
    }

    override fun getItemCount(): Int {
        return animals.size
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