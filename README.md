package vcmsa.ci.musicplaylistmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        val songs = mutableListOf<String>()
        val artists = mutableListOf<String>()
        val ratings = mutableListOf<Int>()
        val comments = mutableListOf<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val songInput = findViewById<EditText>(R.id.songTitle)
        val artistInput = findViewById<EditText>(R.id.artistName)
        val ratingInput = findViewById<EditText>(R.id.rating)
        val commentInput = findViewById<EditText>(R.id.comments)

        val addButton = findViewById<Button>(R.id.addButton)
        val viewButton = findViewById<Button>(R.id.viewButton)
        val exitButton = findViewById<Button>(R.id.exitButton)

        addButton.setOnClickListener {
            val song = songInput.text.toString()
            val artist = artistInput.text.toString()
            val rating = ratingInput.text.toString().toIntOrNull()
            val comment = commentInput.text.toString()

            if (song.isNotBlank() && artist.isNotBlank() && rating != null && rating in 1..5) {
                songs.add(song)
                artists.add(artist)
                ratings.add(rating)
                comments.add(comment)

                Toast.makeText(this, "Item added", Toast.LENGTH_SHORT).show()

                songInput.text.clear()
                artistInput.text.clear()
                ratingInput.text.clear()
                commentInput.text.clear()
            } else {
                Toast.makeText(this, "Please enter valid details. Rating must be between 1 and 5.", Toast.LENGTH_SHORT).show()
            }
        }

        viewButton.setOnClickListener {
            startActivity(Intent(this, ViewActivity::class.java))
        }

        exitButton.setOnClickListener {
            finishAffinity() // Closes the app
        }
    }
}

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
