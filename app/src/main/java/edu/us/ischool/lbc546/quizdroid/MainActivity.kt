package edu.us.ischool.lbc546.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val topics = arrayOf(getString(R.string.math), getString(R.string.physics), getString(R.string.marvel))
        val listView: ListView = findViewById(R.id.list)
        listView.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, topics)
        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, control::class.java)
            intent.putExtra("position", listView.getItemAtPosition(position).toString())
            startActivity(intent)
        }
    }
}
