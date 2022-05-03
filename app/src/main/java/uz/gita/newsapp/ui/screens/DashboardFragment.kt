package uz.gita.newsapp.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newsapp.R
import uz.gita.newsapp.databinding.FragmentDashboardBinding
import uz.gita.newsapp.ui.adapter.DashboardAdapter
import uz.gita.newsapp.viewmodels.DashboardViewModel
import uz.gita.newsapp.viewmodels.impl.DashboardViewModelImpl
//
//@AndroidEntryPoint
//class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
//
//    private val binding by viewBinding(FragmentDashboardBinding::bind)
//    private val viewModel: DashboardViewModel by viewModels<DashboardViewModelImpl>()
//    private val adapter by lazy { DashboardAdapter() }
//    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        viewModel.categoriesLiveData.observe(this, categoriesObserver)
//        viewModel.openArticleLiveData.observe(this, openArticleObserver)
//
//
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//
//        binding.list.adapter = adapter
//        adapter.setItemClickListener {
//            viewModel.openArticleByCategory(it)
//        }
//        binding.favBtn.setOnClickListener {
//            navController.navigate(DashboardFragmentDirections.actionDashboardFragmentToFavArticleFragment())
//        }
//
//    }
//
//    private val categoriesObserver = Observer<List<String>> {
//        adapter.submitList(it)
//    }
//    private val openArticleObserver = Observer<String> {
//        navController.navigate(
//            DashboardFragmentDirections.actionDashboardFragmentToArticleFragment(
//                it
//            )
//        )
//    }
//
//}