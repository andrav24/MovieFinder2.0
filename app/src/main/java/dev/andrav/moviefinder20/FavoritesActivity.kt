package dev.andrav.moviefinder20

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoritesActivity: AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        initRecycler()
    }

    private fun initRecycler() {
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val layoutManager: LinearLayoutManager
        recyclerView.layoutManager = when (this.resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT ->
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            else ->
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        }
        recyclerView.adapter = MovieAdapter(LayoutInflater.from(this), MainActivity.items_fav)
    }

}