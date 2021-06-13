package com.submission.yusuf.home


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.submission.yusuf.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {



    private var activityHomeBinding : ActivityHomeBinding? = null
    private val binding get() = activityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val sectionsPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        binding?.viewPager?.adapter = sectionsPagerAdapter
        binding?.tabs?.setupWithViewPager(binding?.viewPager)

        setupToolbar()

    }



    private fun setupToolbar() {
        supportActionBar?.elevation = 0f
        supportActionBar?.title = "YOUSUF STREAMING"


    }



}