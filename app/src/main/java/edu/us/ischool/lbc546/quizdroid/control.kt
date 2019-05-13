package edu.us.ischool.lbc546.quizdroid

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.FragmentManager

class control : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_main)

        var fragmentManager : FragmentManager = supportFragmentManager
        var fragment: Fragment? = topic()

        var topic = ""

        if (savedInstanceState != null) {
            topic = savedInstanceState.getSerializable("position").toString()
        } else {
            val extras: Bundle = intent.extras
            topic = extras.getString("position")
        }

        if (fragment != null) {
            val ft = fragmentManager.beginTransaction()
            val bundle = Bundle()
            bundle.putString("position", topic)
            fragment.arguments = bundle
            ft.replace(R.id.holder, fragment)
            ft.commit()
        }
    }
}