package uz.gita.newsapp.ui.screens

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newsapp.R
import uz.gita.newsapp.data.model.common.ArticleData
import uz.gita.newsapp.databinding.FragmentArticleBinding
import uz.gita.newsapp.ui.adapter.ArticleAdapter
import uz.gita.newsapp.viewmodels.ArticleViewModel
import uz.gita.newsapp.viewmodels.impl.ArticleViewModelImpl

@AndroidEntryPoint
class ArticleFragment : Fragment(R.layout.fragment_article) {

    private val binding by viewBinding(FragmentArticleBinding::bind)
    private val viewModel: ArticleViewModel by viewModels<ArticleViewModelImpl>()
    private val adapter by lazy { ArticleAdapter() }
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }
    private val args by navArgs<ArticleFragmentArgs>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.list.adapter = adapter

        viewModel.loadArticlesByCategory(args.category)
        viewModel.loadingLiveData.observe(viewLifecycleOwner, loadingObserver)
        viewModel.backLiveData.observe(viewLifecycleOwner, backObserver)
        viewModel.articlesLiveData.observe(viewLifecycleOwner, articleObserver)
        viewModel.updateArticleData.observe(viewLifecycleOwner, updateObserver)

        adapter.setItemClickListener {
            navController.navigate(
                ArticleFragmentDirections.actionArticleFragmentToDetailsFragment(
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

//        adapter.setToggleClickListener {
//            viewModel.updateArticle(it)
//        }
    }


    private val loadingObserver = Observer<Boolean> {
        binding.progress.visibility = if (it) VISIBLE else GONE
    }

    private val articleObserver = Observer<List<ArticleData>> {
        adapter.submitList(it)
    }
    private val updateObserver = Observer<Unit> {
        viewModel.loadArticlesByCategory(args.category)
    }

    private val backObserver = Observer<Unit> {
        requireActivity().onBackPressed()
    }

}