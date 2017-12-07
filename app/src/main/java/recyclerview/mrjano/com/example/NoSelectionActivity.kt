package recyclerview.mrjano.com.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.mooveit.library.Fakeit
import com.mrjano.recyklerview.GenericViewHolder
import kotlinx.android.synthetic.main.activity_no_selection.*
import kotlinx.android.synthetic.main.list_item.view.*

class NoSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_selection)

        recyclerView.setAdapter(R.layout.list_item, { ViewHolder(it) }, { Toast.makeText(this, "Hey!", Toast.LENGTH_SHORT).show() })

        recyclerView.setItems(loadMoreModels())

        recyclerView.setOnScrollWillEndListener {
            loadMoreModels()
            recyclerView.addItems(loadMoreModels())
        }
    }

    fun loadMoreModels(): List<Model> {
        return (0.. 10).map { Model(Fakeit.rickAndMorty().quote()) }
    }

    class ViewHolder(override val view: View): GenericViewHolder<Model>(view) {
        override fun loadView(item: Model) {
            view.title.text = item.title
        }
    }
}



