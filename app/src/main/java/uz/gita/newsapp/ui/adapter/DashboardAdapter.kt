package uz.gita.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.newsapp.databinding.ItemDashboardBinding

class DashboardAdapter : ListAdapter<String, DashboardAdapter.Holder>(DashboardCallback) {
    private var listener: ((String) -> Unit)? = null

    object DashboardCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem

    }

    inner class Holder(private val itemDashboardBinding: ItemDashboardBinding) :
        RecyclerView.ViewHolder(itemDashboardBinding.root) {
        init {
            itemDashboardBinding.root.setOnClickListener {
                listener?.invoke(getItem(bindingAdapterPosition))
            }
        }

        fun bind() {
            itemDashboardBinding.title.text = getItem(bindingAdapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        ItemDashboardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind()

    fun setItemClickListener(l: (String) -> Unit) {
        this.listener = l
    }
}