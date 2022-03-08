package com.example.igift.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.igift.R
import com.example.igift.model.Occasion
import org.w3c.dom.Text

class OccasionAdapter(private val context: Context, private val dateset: List<Occasion>): RecyclerView.Adapter<OccasionAdapter.OccasionViewHolder>() {

    class OccasionViewHolder(private val view : View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.occasionTitleView)
        val imageView : ImageView = view.findViewById(R.id.occasionImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OccasionViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.occasion_item, parent, false)
        return OccasionViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: OccasionViewHolder, position: Int) {
        val item = dateset[position]
        holder.imageView.setImageResource(item.imageResouceId)
        holder.textView.text = context.resources.getString(item.stringResourceId)
    }

    override fun getItemCount(): Int {
        return dateset.size
    }

}