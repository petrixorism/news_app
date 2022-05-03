package uz.gita.newsapp.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.newsapp.ui.screens.NewsListFragment

private const val ARG_OBJECT = "object"

class NewsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 11

    override fun createFragment(position: Int): Fragment {

        val fragment = NewsListFragment()
        fragment.arguments = Bundle().apply {
            putInt("object", position)
        }
        return fragment
    }

}
