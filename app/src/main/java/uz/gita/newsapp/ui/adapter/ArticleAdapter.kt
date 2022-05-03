package uz.gita.newsapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.newsapp.data.model.common.ArticleData
import uz.gita.newsapp.databinding.ItemNewsBinding


class ArticleAdapter : ListAdapter<ArticleData, ArticleAdapter.Holder>(ArticleCallback) {

    private var listener: ((ArticleData) -> Unit)? = null
    private var toggleClick: ((ItemListenerData) -> Unit)? = null

    object ArticleCallback : DiffUtil.ItemCallback<ArticleData>() {
        override fun areItemsTheSame(oldItem: ArticleData, newItem: ArticleData): Boolean =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: ArticleData, newItem: ArticleData): Boolean =
            oldItem.title == newItem.title && oldItem.author == newItem.author
    }

    inner class Holder(private val itemNewsBinding: ItemNewsBinding) :
        RecyclerView.ViewHolder(itemNewsBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind() {
            val item = getItem(bindingAdapterPosition)
            itemNewsBinding.newsTitleTv.text = item.title
            itemNewsBinding.newsDateTv.text = item.time

            Glide
                .with(itemNewsBinding.image)
                .load(item.imageUrl)
                .into(itemNewsBinding.image)

//            itemNewsBinding.btnFav.isChecked = item.isFav
        }

        init {
            itemNewsBinding.root.setOnClickListener {
                listener!!.invoke(getItem(bindingAdapterPosition))
            }

            itemNewsBinding.root.setOnLongClickListener {
                val data = getItem(absoluteAdapterPosition)
                data.isFav = !data.isFav
                toggleClick!!.invoke(ItemListenerData(itemNewsBinding.root, getItem(absoluteAdapterPosition)))

                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind()

    fun setItemClickListener(block: ((ArticleData) -> Unit)) {
        listener = block
    }

    fun setItemOnLongClickListener(block: ((ItemListenerData) -> Unit)) {
        toggleClick = block
    }
}
data class ItemListenerData(val view: View, val data: ArticleData)