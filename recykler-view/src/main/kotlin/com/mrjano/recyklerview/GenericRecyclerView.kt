package com.mrjano.recyklerview

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by jano on 27/11/2017.
 */
abstract class GenericRecyclerView: RecyclerView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        layoutManager = LinearLayoutManager(context)
    }

    fun <T>setItems(items: List<T>) {
        (adapter as GenericRecyclerViewAdapter<T>).setItems(items)
    }

    protected fun <T> getItem(position: Int) : T {
        return (adapter as GenericRecyclerViewAdapter<T>).items[position]
    }

    inner class GenericRecyclerViewAdapter<T>(private val itemLayoutId: Int, private val viewHolder: (View) -> GenericViewHolder<T>, val onBindViewHolderDelegate: (holder: GenericViewHolder<T>, position: Int) -> Unit) : RecyclerView.Adapter<GenericViewHolder<T>>() {
        val items: MutableList<T> = ArrayList()
        fun setItems(items: List<T>) {
            this.items.clear()
            this.items.addAll(items)
            this.notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> {
            val view = LayoutInflater.from(parent.context).inflate(itemLayoutId, parent, false)
            return viewHolder(view)
        }

        override fun onBindViewHolder(holder: GenericViewHolder<T>, position: Int) {
            onBindViewHolderDelegate(holder, position)
        }

        override fun getItemCount(): Int {
            return items.size
        }
    }
}


