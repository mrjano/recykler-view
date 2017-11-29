package recyclerview.mrjano.com.example

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.mooveit.library.Fakeit
import com.mrjano.recyklerview.GenericViewHolder
import kotlinx.android.synthetic.main.activity_multiple_selection.*
import kotlinx.android.synthetic.main.list_item.view.*

class MultipleSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_selection)

        val items = (0..20).map { Model(Fakeit.ancient().titan()) }

        recyclerView.setAdapter(R.layout.list_item, { ViewHolder(it) }, {})
        recyclerView.setItems(items)
    }

    class ViewHolder(override val view: View): GenericViewHolder<Model>(view) {
        override fun loadView(item: Model) {
            view.title.text = item.title
        }

        override fun selectionChanged(option: Boolean) {
            if(option) {
                view.title.setTextColor(Color.RED)
            }
            else {
                view.title.setTextColor(Color.BLACK)
            }
        }

    }
}

