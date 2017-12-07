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

    private var onScrollWillEndListener: (() -> Unit)? = null
    private var onScrollEndSent: Boolean = false

    init {
        layoutManager = LinearLayoutManager(context)
        addOnScrollListener(object: OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                val visibleItems = layoutManager.childCount
                val totalItems = layoutManager.itemCount
                val firstVisibleItem = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if(dy > 0 && visibleItems + firstVisibleItem >= totalItems) {
                    if(!onScrollEndSent) {
                        onScrollWillEndListener?.invoke()
                        onScrollEndSent = true
                    }
                }
                else {
                    onScrollEndSent = false
                }
            }
        })
    }

    fun <T>setItems(items: List<T>) {
        val a = (adapter as GenericRecyclerViewAdapter<T, GenericViewHolder<T>>)
        a.items.clear()
        addItems(items)
    }

    fun <T>addItems(items: List<T>) {
        val a = (adapter as GenericRecyclerViewAdapter<T, GenericViewHolder<T>>)
        a.items.addAll(items)
        //Make sure that the notifyDataSetChanged is done out of a scroll callback
        post({ a.notifyDataSetChanged() })
    }

    fun setOnScrollWillEndListener(onScrollWillEndListener: (() -> Unit)) {
        this.onScrollWillEndListener = onScrollWillEndListener
    }

    protected fun <T> getItem(position: Int) : T {
        return (adapter as GenericRecyclerViewAdapter<T, GenericViewHolder<T>>).items[position]
    }

    inner class GenericRecyclerViewAdapter<T, K: GenericViewHolder<T>>(private val itemLayoutId: Int, private val viewHolder: (View) -> K, val onBindViewHolderDelegate: (holder: K, position: Int) -> Unit) : RecyclerView.Adapter<K>() {
        val items: MutableList<T> = ArrayList()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): K {
            val view = LayoutInflater.from(parent.context).inflate(itemLayoutId, parent, false)
            return viewHolder(view)
        }

        override fun onBindViewHolder(holder: K, position: Int) {
            onBindViewHolderDelegate(holder, position)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun getItemViewType(position: Int): Int {
            return position
        }
    }
}


