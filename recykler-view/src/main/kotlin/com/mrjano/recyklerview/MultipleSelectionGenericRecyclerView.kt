package com.mrjano.recyklerview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * Created by jano on 27/11/2017.
 */
class MultipleSelectionGenericRecyclerView: GenericRecyclerView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val selectedItems: MutableList<Any> = ArrayList()

    fun <T> setAdapter(itemLayoutId: Int, viewHolder: (View) -> SelectableGenericViewHolder<T>, onSelectionChanged: ((List<T>) -> Unit)?) {
        adapter = GenericRecyclerViewAdapter(itemLayoutId, viewHolder, {
            holder, position: Int ->
            Log.e("ERROR", "Binding ${holder.item}")
            holder.item = getItem(position)
            holder.view.setOnClickListener {
                if (holder.selected) {
                    selectedItems.remove(holder.item!! as Any)
                } else {
                    selectedItems.add(holder.item!!)
                }
                holder.selected = !holder.selected
                onSelectionChanged?.invoke(selectedItems as List<T>)
            }
        })
    }
}