package recyclerview.mrjano.com.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.mooveit.library.Fakeit
import com.mrjano.recyklerview.GenericViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Fakeit.initWithLocale("en")

        recyclerView.setAdapter(R.layout.list_item, { ViewHolder(it) }, {})
        recyclerView.setItems(loadMoreModels())

        recyclerView.setOnScrollWillEndListener {
            Log.e("ERROR", "Scroll will end")
            loadMoreModels()
            recyclerView.addItems(loadMoreModels())
        }

    }

    fun loadMoreModels(): List<Model> {
        return (0.. 10).map { Model(Fakeit.rickAndMorty().quote()) }
    }
}

data class Model(val title: String)

class ViewHolder(override val view: View): GenericViewHolder<Model>(view) {
    override fun loadView(item: Model) {
        view.title.text = item.title
    }

    override fun selectionChanged(option: Boolean) {}

}
