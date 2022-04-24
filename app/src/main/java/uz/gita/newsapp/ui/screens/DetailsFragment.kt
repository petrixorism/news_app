package uz.gita.newsapp.ui.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import uz.gita.newsapp.R
import uz.gita.newsapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val binding by viewBinding(FragmentDetailsBinding::bind)
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.apply {

            Glide.with(requireContext())
                .load(args.imgUrl)
                .into(coverIv)
            titleTv.text = args.title
            descriptionTv.text = args.description
            authorTv.text = args.author
            timeTv.text = args.time

            urlBtn.setOnClickListener {
                requireActivity().startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(args.url)))
            }
            backButton.setOnClickListener {
                requireActivity().onBackPressed()
            }

        }

    }

}