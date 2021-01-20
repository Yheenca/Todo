package com.tmr.yheenca.todo.adapter.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tmr.yheenca.todo.ui.screens.fragment.todo_main.TodoFragment
import com.tmr.yheenca.todo.utilities.CustomDate

private val dates = CustomDate.dates
private const val FRAGMENT_ARGS = "dates"

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = dates.size

    override fun createFragment(position: Int): Fragment {
        val fragment = TodoFragment()
        fragment.arguments = Bundle().apply {
            putInt(FRAGMENT_ARGS, position)
        }
        return fragment
    }

}