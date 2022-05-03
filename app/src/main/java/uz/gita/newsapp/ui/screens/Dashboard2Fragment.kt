package uz.gita.newsapp.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newsapp.R
import uz.gita.newsapp.data.model.common.ArticleData
import uz.gita.newsapp.data.source.local.Categories
import uz.gita.newsapp.databinding.FragmentDashboard2Binding
import uz.gita.newsapp.ui.adapter.NewsPagerAdapter
import uz.gita.newsapp.viewmodels.DashboardViewModel
import uz.gita.newsapp.viewmodels.impl.DashboardViewModelImpl
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class Dashboard2Fragment : Fragment(R.layout.fragment_dashboard2) {

    private val binding by viewBinding(FragmentDashboard2Binding::bind)
    private val viewModel: DashboardViewModel by viewModels<DashboardViewModelImpl>()
    private var articleData: ArticleData? = null

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val demoCollectionPagerAdapter = NewsPagerAdapter(this)
        binding.pager.adapter = demoCollectionPagerAdapter

        binding.tabLayout.tabMode = TabLayout.MODE_SCROLLABLE

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->

            val catg = Categories.getAllCategory()[position]
            tab.text = catg[0].toUpperCase().toString() + catg.substring(1, catg.length)
        }.attach()

        val sdf = SimpleDateFormat("dd-MMMM yyyy")
        val netDate = Date(System.currentTimeMillis())
        val time = sdf.format(netDate)
        binding.todayDateTv.text = time

        binding.goToBookmarksBtn.setOnClickListener {
            findNavController().navigate(
                Dashboard2FragmentDirections.actionDashboard2FragmentToFavArticleFragment()
            )
        }



        binding.image.setOnClickListener {
            articleData?.let {
                findNavController().navigate(
                    Dashboard2FragmentDirections.actionDashboard2FragmentToDetailsFragment(
                        articleData!!.title,
                        articleData!!.author,
                        articleData!!.description,
                        articleData!!.imageUrl,
                        articleData!!.readMoreUrl,
                        articleData!!.time,
                        articleData!!.url
                    )
                )
            }


        }




        viewModel.oneArticleLiveData.observe(viewLifecycleOwner) { data ->
            binding.newsTitleTv.text = data.title
            binding.image.apply {
                Glide
                    .with(requireContext())
                    .load(data.imageUrl)
                    .placeholder(R.raw.img)
                    .into(this)
            }
            articleData = data
        }
    }
}