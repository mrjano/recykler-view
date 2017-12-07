package recyclerview.mrjano.com.example

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.mooveit.library.Fakeit
import com.mrjano.recyklerview.SelectableGenericViewHolder
import kotlinx.android.synthetic.main.activity_single_selection.*
import kotlinx.android.synthetic.main.list_item.view.*
import java.util.*

class SingleSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_selection)

        val items = (0..20).map { Model(Fakeit.gameOfThrones().house()) }

        recyclerView.setAdapter(R.layout.list_item, { ViewHolder(it) }, {})
        recyclerView.setItems(items)

    }

    class ViewHolder(override val view: View): SelectableGenericViewHolder<Model>(view) {
        override fun loadView(item: Model) {
            view.title.text = item.title
        }

        override fun selectionChanged(option: Boolean) {
            if(option) {
                val rnd = Random()
                view.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)))
            }
            else {
                view.setBackgroundColor(Color.WHITE)
            }
        }

    }
}

