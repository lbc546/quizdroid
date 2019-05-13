package edu.us.ischool.lbc546.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Button

class answer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.answer)

        val mathAnswer = arrayOf(getString(R.string.ma3), getString(R.string.ma2), getString(R.string.ma1))
        val physicsAnswer = arrayOf(getString(R.string.pa3), getString(R.string.pa2), getString(R.string.pa1))
        val marvelAnswer = arrayOf(getString(R.string.mwa1), getString(R.string.mwa2), getString(R.string.mwa3))

        var answer = ""
        var key = ""
        var score : Int
        var topic  = ""
        var question  = 0

        if (savedInstanceState != null) {
            answer = savedInstanceState.getSerializable("answer").toString()
            topic = savedInstanceState.getSerializable("topic").toString()
            question = savedInstanceState.getInt("question")
            score = savedInstanceState.getInt("correct")
        } else {
            val extras: Bundle = intent.extras
            answer = extras.getString("answer")
            topic = extras.getString("topic")
            question = extras.getInt("question")
            score = extras.getInt("correct")
        }

        when (topic) {
            "Math" -> {
                key = mathAnswer[2 - question]
            }
            "Physics" -> {
                key = physicsAnswer[2 - question]
            }
            "Marvel Super Heroes" -> {
                key = marvelAnswer[question]
            }
        }

        if (answer == key) {
            score++
        }

        val currentScore : TextView = findViewById(R.id.score)
        findViewById<TextView>(R.id.answer).text = "Your answer was: " + answer
        findViewById<TextView>(R.id.key).text = "The correct answer is: " + key
        currentScore.text = "You have " + score.toString() + " out of 3 correct"

        val nextButton : Button = findViewById(R.id.next)
        if (question < 2) {
            nextButton.text = "Next"
            nextButton.setOnClickListener {
                val intent = Intent(this, quiz::class.java)
                intent.putExtra("topic", topic)
                intent.putExtra("question", question + 1)
                intent.putExtra("correct", score)
                startActivity(intent)
            }
        } else {
            nextButton.text = "Finish"
            nextButton.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

}