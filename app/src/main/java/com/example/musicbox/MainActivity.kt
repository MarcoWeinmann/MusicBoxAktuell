package com.example.musicbox

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var playButton = findViewById<ImageButton>(R.id.play_button)
        playButton.setOnClickListener(
            View.OnClickListener {
                createSongText()
            }
        )
    }

    fun getGenreFromRadios(): String {
        try {
            var genre = findViewById<RadioButton>(findViewById<RadioGroup>(R.id.music_type_radio).checkedRadioButtonId).text.toString()
            return genre
        } catch (ex: Exception) {
            return ex.toString()
        }
    }

    fun getSongLength(): Float {
        var songLength = findViewById<Slider>(R.id.song_text_slider).value
        return songLength
    }

    fun getStringFromInput(tvId: Int): String {
        var str = findViewById<EditText>(tvId).text.toString()
        return str
    }

    fun createSongString(genre_strophe: String, genre_hook: String, songLength: Int): String {
        return ((genre_strophe) + genre_hook.repeat(3)).repeat(songLength)
    }

    fun createSongText() {
        var t1 = getStringFromInput(R.id.firstText)
        var t2 = getStringFromInput(R.id.secondText)
        var t3 = getStringFromInput(R.id.thirdText)
        var genre = getGenreFromRadios()
        var songLength = getSongLength()
        var songString: String = ""
        var sV = findViewById<TextView>(R.id.song_text_tv)
        sV.setMovementMethod(ScrollingMovementMethod())
        // getStringFromRessource(genre)
        // getStringFromRessource(genre)
        when (genre) {
            "Rap" -> {
                var rap_strophe = getString(R.string.rap_song, t1, t2, t3)
                var rap_ref = getString(R.string.rap_song_ref)
                songString = createSongString(rap_strophe, rap_ref, songLength.toInt())
                sV.text = songString
            }
            "Pop" -> {
                var pop_strophe = getString(R.string.pop_song, t1, t2, t3)
                var pop_ref = getString(R.string.pop_ref)
                songString = createSongString(pop_strophe, pop_ref, songLength.toInt())
                sV.text = songString
            }
            "Volk" -> {
                var volk_strophe = getString(R.string.volk_song, t1, t2)
                var volk_ref = getString(R.string.volk_song_ref, t3)
                songString = createSongString(volk_strophe, volk_ref, songLength.toInt())
                sV.text = songString
            }
            else -> {
                sV.text = getString(R.string.genre_error)
            }
        }
    }
}
