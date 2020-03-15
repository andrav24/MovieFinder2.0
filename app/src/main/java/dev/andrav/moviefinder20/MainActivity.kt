package dev.andrav.moviefinder20

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), CustomDialogFragment.CustomDialogListener {
    private lateinit var btnFav : Button
    private lateinit var recyclerView: RecyclerView
    private val items = arrayListOf<MovieItem>(
        MovieItem(R.drawable.film_batman_small,"Batman1","film description"),
        MovieItem(R.drawable.film_joker_small,"Joker1","film description"),
        MovieItem(R.drawable.film_batman_small,"Batman2","film description"),
        MovieItem(R.drawable.film_joker_small,"Joker2","film description"),
        MovieItem(R.drawable.film_batman_small,"Batman3","film description"),
        MovieItem(R.drawable.film_joker_small,"Joker3","film description"),
        MovieItem(R.drawable.film_batman_small,"Batman4","film description"),
        MovieItem(R.drawable.film_joker_small,"Joker4","film description"),
        MovieItem(R.drawable.film_batman_small,"Batman5","film description"),
        MovieItem(R.drawable.film_joker_small,"Joker5","film description"),
        MovieItem(R.drawable.film_batman_small,"Batman6","film description"),
        MovieItem(R.drawable.film_joker_small,"Joker6","film description"),
        MovieItem(R.drawable.film_batman_small,"Batman7","film description"),
        MovieItem(R.drawable.film_joker_small,"Joker7","film description")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        initRecycler()
    }

    override fun onRestart() {
        // не получилось использовать этот метод, некорректно отображался список после возврата из списка Избранного
        //recyclerView.adapter?.notifyDataSetChanged()

        initRecycler()
        super.onRestart()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_favorites) {
            val intent = Intent(this@MainActivity, FavoritesActivity::class.java)
            startActivity(intent)
            return true
        }
        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
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
        recyclerView.adapter = MovieAdapter(LayoutInflater.from(this), items)
    }

    override fun onBackPressed() {
        val dialogFragment = CustomDialogFragment()
        dialogFragment.show(supportFragmentManager, "CustomDialogFragment")
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        super.onBackPressed()
    }

    companion object {
        val TAG = MainActivity::class.java.simpleName
        const val KEY_JOKER    = "joker"
        const val KEY_BATMAN   = "batman"
        const val FILM_REQUEST_CODE = 42
        const val MESSAGE_FROM_CHECKBOX = "message_from_checkbox"
        const val MESSAGE_FROM_COMMENT = "message_from_comment"
        val items_fav = arrayListOf<MovieItem>()
    }
}
