package com.example.apphoroscopo.activities

import android.os.Bundle
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apphoroscopo.HoroscopeAdapter
import com.example.apphoroscopo.data.*
import com.example.apphoroscopo.data.HoroscopeProvider
import com.example.apphoroscopo.R
import android.content.Intent
import com.example.apphoroscopo.activities.DetailActivity // o la ruta correcta



class MainActivity : AppCompatActivity() {

    var horoscopeList= HoroscopeProvider.getAll()

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: HoroscopeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView)
    }

    override fun onResume() {
        super.onResume()
        adapter = HoroscopeAdapter(horoscopeList, { position ->
            val horoscope = horoscopeList[position]

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("HOROSCOPE_ID", horoscope.id)
            startActivity(intent)
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)

        val menuItem = menu.findItem(R.id.menu_search)
        val searchView = menuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                horoscopeList = HoroscopeProvider.Companion.getAll().filter { horoscope ->
                    getString(horoscope.name).contains(newText, true)
                }
                adapter.updateItems(horoscopeList)
                return true
            }
        })

        return true
    }
}