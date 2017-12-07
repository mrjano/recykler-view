package com.mrjano.recyklerview

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * Created by jano on 27/11/2017.
 */
class SingleSelectionGenericRecyclerView: GenericRecyclerView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var selectedHolder: SelectableGenericViewHolder<Any>? = null

    fun <T> setAdapter(itemLayoutId: Int, viewHolder: (View) -> SelectableGenericViewHolder<T>, onSelectionChanged: ((T?) -> Unit)?) {
        adapter = GenericRecyclerViewAdapter(itemLayoutId, viewHolder, {
            holder, position ->
            holder.item = getItem(position)
            holder.view.setOnClickListener {
                selectedHolder = if (holder.selected) {
                    null
                } else {
                    selectedHolder?.selected = false
                    holder as SelectableGenericViewHolder<Any>
                }
                holder.selected = !holder.selected

                onSelectionChanged?.invoke(if (selectedHolder == null) null else selectedHolder!!.item as T)
            }
        })
    }
}