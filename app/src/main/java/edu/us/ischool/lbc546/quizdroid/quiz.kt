package edu.us.ischool.lbc546.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.*

class quiz : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz)

        val mathQuestions = arrayOf(getString(R.string.mq1), getString(R.string.mq2), getString(R.string.mq3))
        val physicsQuestions = arrayOf(getString(R.string.pq1), getString(R.string.pq2), getString(R.string.pq3))
        val marvelQuestions = arrayOf(getString(R.string.mwq1), getString(R.string.mwq2), getString(R.string.mwq3))
        val mathChoices = arrayOf(getString(R.string.ma3), getString(R.string.ma2), getString(R.string.ma1))
        val physicsChoices = arrayOf(getString(R.string.pa3), getString(R.string.pa2), getString(R.string.pa1))
        val marvelChoices = arrayOf(getString(R.string.mwa1), getString(R.string.mwa2), getString(R.string.mwa3))
        val group : RadioGroup = findViewById(R.id.choices)
        fun addButtons (choices: Array<String>) {
            for (choice in choices) {
                val button = RadioButton(this)
                button.text = choice
                group.addView(button)
            }
        }
        var questionLine : TextView = findViewById(R.id.question)
        var question = 0
        var correct = 0
        var topic = ""

        if (savedInstanceState != null) {
            topic = savedInstanceState.getSerializable("topic").toString()
            question = savedInstanceState.getInt("question")
            correct = savedInstanceState.getInt("correct")
        } else {
            val extras: Bundle = intent.extras
            topic = extras.getString("topic")
            question = extras.getInt("question")
            correct = extras.getInt("correct")
        }

        when (topic) {
            "Math" -> {
                addButtons(mathChoices)
                questionLine.text = mathQuestions[question]
            }
            "Physics" -> {
                addButtons(physicsChoices)
                questionLine.text = physicsQuestions[question]
            }
            "Marvel Super Heroes" -> {
                addButtons(marvelChoices)
                questionLine.text = marvelQuestions[question]
            }
        }

        val submit : Button = findViewById(R.id.submit)
        submit.setOnClickListener{
            if (findViewById<Button>(group.checkedRadioButtonId) != null) {
                val button: Button = findViewById(group.checkedRadioButtonId)
                val intent = Intent(this, answer::class.java)
                intent.putExtra("topic", topic)
                intent.putExtra("question", question)
                intent.putExtra("answer", button.text.toString())
                intent.putExtra("correct", correct)
                startActivity(intent)
            } else {
                val intent = Intent(this, quiz::class.java)
                intent.putExtra("topic", topic)
                intent.putExtra("question", question)
                intent.putExtra("correct", correct)
                startActivity(intent)
            }
        }
    }
}