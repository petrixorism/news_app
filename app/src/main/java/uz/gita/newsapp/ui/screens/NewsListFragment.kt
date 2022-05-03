package uz.gita.newsapp.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newsapp.R
import uz.gita.newsapp.data.model.common.ArticleData
import uz.gita.newsapp.data.source.local.Categories
import uz.gita.newsapp.data.source.local.Categories.ARG_OBJECT
import uz.gita.newsapp.databinding.FragmentArticleBinding
import uz.gita.newsapp.ui.adapter.ArticleAdapter
import uz.gita.newsapp.viewmodels.ArticleViewModel
import uz.gita.newsapp.viewmodels.impl.ArticleViewModelImpl

@AndroidEntryPoint
class NewsListFragment : Fragment(R.layout.fragment_article) {

    private val binding by viewBinding(FragmentArticleBinding::bind)
    private val viewModel: ArticleViewModel by viewModels<ArticleViewModelImpl>()
    private val adapter by lazy { ArticleAdapter() }
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }
    private lateinit var category: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            category = Categories.getAllCategory()[getInt(ARG_OBJECT)]
            viewModel.loadArticlesByCategory(category)
        }
        binding.list.adapter = adapter


        viewModel.loadingLiveData.observe(viewLifecycleOwner, loadingObserver)
        viewModel.backLiveData.observe(viewLifecycleOwner, backObserver)
        viewModel.articlesLiveData.observe(viewLifecycleOwner, articleObserver)
        viewModel.updateArticleData.observe(viewLifecycleOwner, updateObserver)

        adapter.setItemClickListener {
            navController.navigate(
                Dashboard2FragmentDirections.actionDashboard2FragmentToDetailsFragment(
                    it.title,
                    it.author,
                    it.description,
                    it.imageUrl,
                    it.readMoreUrl,
                    it.time,
                    it.url
                )
            )
        }

        adapter.setItemOnLongClickListener { data->
            showPopUp(data.view, data.data)
        }

    }

    private val loadingObserver = Observer<Boolean> {
        binding.progress.visibility = if (it) View.VISIBLE else View.GONE
    }

    private val articleObserver = Observer<List<ArticleData>> {
        adapter.submitList(it)
    }
    private val updateObserver = Observer<Unit> {
        viewModel.loadArticlesByCategory(category)
    }

    private val backObserver = Observer<Unit> {
        requireActivity().onBackPressed()
    }

    private fun showPopUp(view: View, data: ArticleData) {
        val popup = PopupMenu(requireContext(), view)
        popup.inflate(R.menu.item_menu)
        popup.show()
        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.addToFav -> {
                    data.let {
                        viewModel.updateArticle(data)
                    }
                    return@setOnMenuItemClickListener true
                }
            }

            return@setOnMenuItemClickListener false
        }
    }


}