package edu.us.ischool.lbc546.quizdroid

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class topic : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView: View = inflater.inflate(R.layout.topic, container, false)
        val topicLine: TextView = rootView.findViewById(R.id.topic_line)
        val topic = arguments!!.getString("position")
        val overview: TextView = rootView.findViewById(R.id.description)
        var fragmentManager: FragmentManager = activity!!.supportFragmentManager

        topicLine.text = topic

        when (topicLine.text) {
            "Math" -> overview.text = getString(R.string.math_description)
            "Physics" -> overview.text = getString(R.string.physics_description)
            "Marvel Super Heroes" -> overview.text = getString(R.string.marvel_description)
        }

        val begin: Button = rootView.findViewById(R.id.start)
        begin.setOnClickListener {
            val fragment: Fragment = quiz()
            val ft = fragmentManager.beginTransaction()
            var bundle = Bundle()
            bundle.putString("topic", topic)
            bundle.putInt("question", 0)
            bundle.putInt("correct", 0)
            fragment.arguments = bundle
            ft.replace(R.id.holder, fragment)
            ft.addToBackStack(null)
            ft.commit()
        }
        return rootView
    }

}