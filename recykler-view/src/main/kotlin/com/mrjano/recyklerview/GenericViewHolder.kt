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
    abstract fun loadView(item: T)
}