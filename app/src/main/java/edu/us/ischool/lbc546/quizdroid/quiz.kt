package edu.us.ischool.lbc546.quizdroid

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class quiz : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val mathQuestions = arrayOf(getString(R.string.mq1), getString(R.string.mq2), getString(R.string.mq3))
        val physicsQuestions = arrayOf(getString(R.string.pq1), getString(R.string.pq2), getString(R.string.pq3))
        val marvelQuestions = arrayOf(getString(R.string.mwq1), getString(R.string.mwq2), getString(R.string.mwq3))
        val mathChoices = arrayOf(getString(R.string.ma3), getString(R.string.ma2), getString(R.string.ma1))
        val physicsChoices = arrayOf(getString(R.string.pa3), getString(R.string.pa2), getString(R.string.pa1))
        val marvelChoices = arrayOf(getString(R.string.mwa1), getString(R.string.mwa2), getString(R.string.mwa3))

        val rootView : View = inflater.inflate(R.layout.quiz, container, false)
        val group : RadioGroup = rootView.findViewById(R.id.choices)
        fun addButtons (choices: Array<String>) {
            for (choice in choices) {
                val button = RadioButton(context)
                button.text = choice
                group.addView(button)
            }
        }
        var questionLine : TextView = rootView.findViewById(R.id.question)
        var fragmentManager : FragmentManager = activity!!.supportFragmentManager

        var topic= arguments!!.getString("topic")
        var question = arguments!!.getInt("question")
        var correct = arguments!!.getInt("correct")

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

        val submit : Button = rootView.findViewById(R.id.submit)
        submit.setOnClickListener{
            if (rootView.findViewById<Button>(group.checkedRadioButtonId) != null) {
                val button: Button = rootView.findViewById(group.checkedRadioButtonId)
                val ft = fragmentManager.beginTransaction()
                val fragment : Fragment = answer()
                val bundle = Bundle()
                bundle.putString("topic", topic)
                bundle.putInt("question", question)
                bundle.putString("answer", button.text.toString())
                bundle.putInt("correct", correct)
                fragment.arguments = bundle
                ft.replace(R.id.holder, fragment)
                ft.commit()
            } else {
                val ft = fragmentManager.beginTransaction()
                val fragment : Fragment = quiz()
                val bundle = Bundle()
                bundle.putString("topic", topic)
                bundle.putInt("question", question)
                bundle.putInt("correct", correct)
                fragment.arguments = bundle
                ft.replace(R.id.holder, fragment)
                ft.commit()
            }
        }
        return rootView
    }
}