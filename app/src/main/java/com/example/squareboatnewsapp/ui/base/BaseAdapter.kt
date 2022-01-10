package com.example.squareboatnewsapp.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * @Author: Akshay Sharma
 * @Date: 09/01/22
 */
private typealias ViewHolderViewBindingInflater<VB> = (
    inflater: LayoutInflater,
    parent: ViewGroup,
    attachToParent: Boolean
) -> VB

abstract class BaseAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    fun <VB : ViewBinding> ViewGroup.inflateBinding(
        bindingInflater: ViewHolderViewBindingInflater<VB>
    ): VB {
        return bindingInflater.invoke(LayoutInflater.from(context), this, false)
    }

    abstract class BaseViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)
}