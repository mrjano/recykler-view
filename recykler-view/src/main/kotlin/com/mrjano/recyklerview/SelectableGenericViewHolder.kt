package com.mrjano.recyklerview

import android.view.View

/**
 * Created by jano on 06/12/2017.
 */
abstract class SelectableGenericViewHolder<T>(override val view: View) : GenericViewHolder<T>(view) {
    var selected: Boolean = false
        set(value) {
            field = value
            selectionChanged(value)
        }
    abstract fun selectionChanged(option: Boolean)
}