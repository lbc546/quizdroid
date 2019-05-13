package edu.us.ischool.lbc546.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class topic : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.topic)

        val topicLine: TextView = findViewById(R.id.topic_line)
        val overview: TextView = findViewById(R.id.description)

        if (savedInstanceState != null) {
            topicLine.text = savedInstanceState.getSerializable("position").toString()
        } else {
            val extras: Bundle = intent.extras
            topicLine.text = extras.getString("position")
        }

        when (topicLine.text) {
            "Math" -> overview.text = getString(R.string.math_description)
            "Physics" -> overview.text = getString(R.string.physics_description)
            "Marvel Super Heroes" -> overview.text = getString(R.string.marvel_description)
        }

        val begin: Button = findViewById(R.id.start)
        begin.setOnClickListener {
            val intent = Intent(this, quiz::class.java)
            intent.putExtra("topic", topicLine.text)
            intent.putExtra("question", 0)
            intent.putExtra("correct", 0)
            startActivity(intent)
        }
    }
}