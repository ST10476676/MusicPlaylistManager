package vcmsa.ci.musicplaylistmanager

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        val listView = findViewById<ListView>(R.id.itemListView)
        val displayButton = findViewById<Button>(R.id.displayButton)
        val backButton = findViewById<Button>(R.id.backButton)

        displayButton.setOnClickListener {
            val displayList = mutableListOf<String>()

            for (i in MainActivity.songs.indices) {
                val item = "Song Title: ${MainActivity.songs[i]}\n" +
                        "Artist: ${MainActivity.artists[i]}\n" +
                        "Rating: ${MainActivity.ratings[i]}\n" +
                        "Comment: ${MainActivity.comments[i]}"
                displayList.add(item)
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, displayList)
            listView.adapter = adapter
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}