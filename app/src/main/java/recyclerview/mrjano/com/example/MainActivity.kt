package recyclerview.mrjano.com.example

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mooveit.library.Fakeit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Fakeit.initWithLocale("en")

        noSelection.setOnClickListener {
            val intent = Intent(this@MainActivity, NoSelectionActivity::class.java)
            startActivity(intent)
        }

        singleSelection.setOnClickListener {
            val intent = Intent(this@MainActivity, SingleSelectionActivity::class.java)
            startActivity(intent)
        }

        multipleSelection.setOnClickListener {
            val intent = Intent(this@MainActivity, MultipleSelectionActivity::class.java)
            startActivity(intent)
        }

    }



}