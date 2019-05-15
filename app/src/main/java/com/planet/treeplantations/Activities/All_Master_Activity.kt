package com.planet.treeplantations.Activities

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.planet.treeplantations.R
import com.planet.treeplantations.adapter.All_Master_Pager_Adapter

class All_Master_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all__master_)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        title = "All Master"

        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        if (viewPager != null) {
            val adapter = All_Master_Pager_Adapter(supportFragmentManager)
            viewPager.adapter = adapter
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        finish()
        return true
    }
}
