package com.router.nftforum.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.router.nftforum.binding.NaverNewsHolderModel
import com.router.nftforum.databinding.HolderNewsBinding

class NewsRecyclerViewAdapter(private val modelList: List<NaverNewsHolderModel>, private val clickUnit: (url: String) ->Unit): RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewDataBinding = HolderNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val holderModel = modelList[position]
        holder.bind(holderModel)
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    inner class ViewHolder(val viewDataBinding: HolderNewsBinding): RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(holderModel: NaverNewsHolderModel) {
            viewDataBinding.holderModel = holderModel

            itemView.setOnClickListener {
                clickUnit(holderModel.link)
            }
        }
    }
}