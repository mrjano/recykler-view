package com.mrjano.recyklerview

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by jano on 27/11/2017.
 */
abstract class GenericViewHolder<T>(open val view: View) : RecyclerView.ViewHolder(view) {
    var item: T? = null
        set(value) {
            field = value
            loadView(value!!)
        }
    var selected: Boolean = false
        set(value) {
            field = value
            selectionChanged(value)
        }
    abstract fun loadView(item: T)
    abstract fun selectionChanged(option: Boolean)
}