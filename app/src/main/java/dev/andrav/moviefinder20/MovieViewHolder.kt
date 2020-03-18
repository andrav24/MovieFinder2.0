package dev.andrav.moviefinder20

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    val image_movie: ImageView = item.findViewById(R.id.image_movie)
    val title_movie: TextView = item.findViewById(R.id.title_movie)
    val button_movie: Button = item.findViewById(R.id.button_details)
    val image_fav: ImageView = item.findViewById(R.id.image_fav)


    fun bind(item: MovieItem) {
        image_movie.setImageResource(item.image)
        title_movie.text = item.title

        if (isFavorites(item)) {
            image_fav.setImageResource(R.drawable.ic_favorite_red_24dp)
        }

        button_movie.setOnClickListener {
            val intent = Intent(it.context,SecondActivity::class.java)
            intent.putExtra("film_extra", item.image)
            it.context.startActivity(intent)
        }

        image_fav.setOnClickListener {
            if (isFavorites(item)) {
                MainActivity.items_fav.remove(item)
                image_fav.setImageResource(R.drawable.ic_favorite_border_gray_24dp)
            } else {
                MainActivity.items_fav.add(item)
                image_fav.setImageResource(R.drawable.ic_favorite_red_24dp)
            }
        }
    }

    private fun isFavorites(item: MovieItem) : Boolean {
        return MainActivity.items_fav.contains(item)
    }
}