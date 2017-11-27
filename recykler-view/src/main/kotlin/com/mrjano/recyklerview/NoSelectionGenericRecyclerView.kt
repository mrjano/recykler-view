package com.mrjano.recyklerview

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * Created by jano on 27/11/2017.
 */
class NoSelectionGenericRecyclerView: GenericRecyclerView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun <T> setAdapter(itemLayoutId: Int, viewHolder: (View) -> GenericViewHolder<T>, onItemClickListener: ((T) -> Unit)?) {
        adapter = GenericRecyclerViewAdapter(itemLayoutId, viewHolder, {
            holder, position ->
            holder.item = getItem(position)
            holder.view.setOnClickListener { onItemClickListener?.invoke(holder.item!!) }
        })
    }
}