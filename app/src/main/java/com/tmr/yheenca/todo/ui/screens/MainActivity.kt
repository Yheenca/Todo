package com.tmr.yheenca.todo.ui.screens

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tmr.yheenca.todo.R
import com.tmr.yheenca.todo.adapter.viewpager.ViewPagerAdapter
import com.tmr.yheenca.todo.databinding.ActivityMainBinding
import com.tmr.yheenca.todo.ui.screens.fragment.add_todo.AddTodoFragment
import com.tmr.yheenca.todo.utilities.CustomDate
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*

private const val monthPattern = "d"
private const val dayPattern = "EEE"
private const val todayPattern = "EEE MMM, d"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val date by lazy {
        Date(System.currentTimeMillis())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        val months = CustomDate.convertDate(monthPattern)
        val days = CustomDate.convertDate(dayPattern)

        binding.apply {
            setSupportActionBar(mainToolbar)

            viewPager.adapter = viewPagerAdapter

            TabLayoutMediator(tabLayout, viewPager){ tab, pos ->
                tab.setCustomView(R.layout.custom_tab)
                tab.customView?.run {
                    this.findViewById<TextView>(R.id.tab_day_text).text = days[pos]
                    this.findViewById<TextView>(R.id.tab_month_text).text = months[pos]
                }
            }.attach()

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.customView?.run{
                        this.findViewById<TextView>(R.id.tab_day_text)
                            .setTextColor(ContextCompat.getColor(context, R.color.colorPurple))
                        this.findViewById<TextView>(R.id.tab_month_text)
                            .setTextColor(ContextCompat.getColor(context, R.color.colorPurple))
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    tab?.customView?.run{
                        this.findViewById<TextView>(R.id.tab_day_text)
                            .setTextColor(ContextCompat.getColor(context, R.color.colorText))
                        this.findViewById<TextView>(R.id.tab_month_text)
                            .setTextColor(ContextCompat.getColor(context, R.color.colorDark))
                    }
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    tab?.customView?.run{
                        this.findViewById<TextView>(R.id.tab_day_text)
                            .setTextColor(ContextCompat.getColor(context, R.color.colorPurple))
                        this.findViewById<TextView>(R.id.tab_month_text)
                            .setTextColor(ContextCompat.getColor(context, R.color.colorPurple))
                    }
                }

            })

            tabLayout.getTabAt(getToday(days))?.select()

            addTodoButton.setOnClickListener {
                navigateToBottomSheet()
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        invalidateOptionsMenu()
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.findItem(R.id.menu_today)?.title = getToday()
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private fun getToday(days: List<String>): Int {
        val format = SimpleDateFormat(dayPattern, Locale.getDefault())
        val day =  days.find{ it == format.format(date)}
        return days.indexOf(day)
    }

    private fun getToday(): String {
        val format = SimpleDateFormat(todayPattern, Locale.getDefault())
        return format.format(date)
    }

    private fun navigateToBottomSheet(){
        val bottomSheet = AddTodoFragment.newInstance()
        bottomSheet.show(supportFragmentManager, AddTodoFragment.TAG)
    }

}