package edu.us.ischool.lbc546.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Button

class answer : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView: View = inflater.inflate(R.layout.answer, container, false)
        var fragmentManager: FragmentManager = activity!!.supportFragmentManager

        val mathAnswer = arrayOf(getString(R.string.ma3), getString(R.string.ma2), getString(R.string.ma1))
        val physicsAnswer = arrayOf(getString(R.string.pa3), getString(R.string.pa2), getString(R.string.pa1))
        val marvelAnswer = arrayOf(getString(R.string.mwa1), getString(R.string.mwa2), getString(R.string.mwa3))

        var answer = arguments!!.getString("answer")
        var key = ""
        var score = arguments!!.getInt("correct")
        var topic = arguments!!.getString("topic")
        var question = arguments!!.getInt("question")

        when (topic) {
            "Math" -> key = mathAnswer[2 - question]
            "Physics" -> key = physicsAnswer[2 - question]
            "Marvel Super Heroes" -> key = marvelAnswer[question]
        }

        if (answer == key) {
            score++
        }

        val currentScore: TextView = rootView.findViewById(R.id.score)
        rootView.findViewById<TextView>(R.id.answer).text = "Your answer was: " + answer
        rootView.findViewById<TextView>(R.id.key).text = "The correct answer is: " + key
        currentScore.text = "You have " + score.toString() + " out of 3 correct"

        val nextButton: Button = rootView.findViewById(R.id.next)
        if (question < 2) {
            nextButton.text = "Next"
            nextButton.setOnClickListener {
                val fragment: Fragment = quiz()
                val ft = fragmentManager.beginTransaction()
                var bundle = Bundle()
                bundle.putString("topic", topic)
                bundle.putInt("question", question + 1)
                bundle.putInt("correct", score)
                fragment.arguments = bundle
                ft.replace(R.id.holder, fragment)
                ft.addToBackStack(null)
                ft.commit()
            }
        } else {
            nextButton.text = "Finish"
            nextButton.setOnClickListener {
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            }
        }
        return rootView
    }
}