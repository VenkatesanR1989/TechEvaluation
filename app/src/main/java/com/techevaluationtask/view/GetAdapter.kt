package com.techevaluationtask.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.techevaluationtask.R
import com.techevaluationtask.databinding.GetRowItemBinding
import com.techevaluationtask.model.repository.GetPayload
import java.util.*


class GetAdapter(context: Context,getList: ArrayList<GetPayload>) : RecyclerView.Adapter<GetAdapter.MyViewHolder>() {

    private val getList: ArrayList<GetPayload>
    private var layoutInflater: LayoutInflater? = null
    var mContext:Context?=null

     class MyViewHolder(val binding: GetRowItemBinding) : RecyclerView.ViewHolder(binding.getRoot())

    init {
        this.mContext=context
        this.getList = getList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val binding = DataBindingUtil.inflate<GetRowItemBinding>(layoutInflater!!, R.layout.get_row_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.title.text = getList[position].title
        holder.binding.description.text = getList[position].description
        Glide.with(mContext!!).load(Uri.parse(getList[position].image)).into(holder.binding.thumbnail)
    }

    override fun getItemCount(): Int {
        return getList.size
    }


}
