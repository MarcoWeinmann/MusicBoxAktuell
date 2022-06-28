package com.example.musicbox

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.musicbox.databinding.ActivityMainBinding
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       binding.playButton.setOnClickListener(
            View.OnClickListener {
                createSongText()
            }
        )
    }

    /**
     * diese Funktion gibt den Wert des ausgewählten RadioButtons zurück
     **/
    fun getGenreFromRadios(): Int {
        var genre = binding.musicTypeRadio.checkedRadioButtonId
        return genre
    }

    /**
     * diese Funktion gibt den Wert des Sliders zurück
     **/
    fun getSongLength(): Float {
        var songLength = binding.songTextSlider.value
        return songLength
    }

    /**
     * Diese Funktion liest die EditText Felder aus und gibt den Wert zurück
     **/
    fun getStringFromInput(tvId: Int): String {
        var str = findViewById<EditText>(tvId).text.toString()
        return str
    }

    /**
     * Diese Funktion  führt Verse und Refrain zusammen und wiederholt je nach songLength
     **/
    fun createSongString(genreVerse: String, genreChorus: String, songLength: Int): String {
        return ((genreVerse) + genreChorus.repeat(3)).repeat(songLength)
    }

    /**
     * diese Funktion führ die vorher implementierten Funktionen aus, um die Infos asuzulesen und zusammenzuführen
     * Außerdem wird der erstellte songString in der entsprechenden View dargestellt
     **/
    fun createSongText() {
        var t1 = getStringFromInput(R.id.firstText)
        var t2 = getStringFromInput(R.id.secondText)
        var t3 = getStringFromInput(R.id.thirdText)
        var genre = getGenreFromRadios()
        var songLength = getSongLength()
        var songString: String = ""
        var sV = findViewById<TextView>(R.id.song_text_tv)
        sV.setMovementMethod(ScrollingMovementMethod())
        var songVerse: String = ""
        var songChorus: String = ""

        when (genre) {
            R.id.radio_rap -> {
                songVerse = getString(R.string.rap_verse, t1, t2, t3)
                songChorus = getString(R.string.rap_chorus)
            }
            R.id.radio_pop -> {
                songVerse = getString(R.string.pop_verse, t1, t2, t3)
                songChorus = getString(R.string.pop_chorus)
            }
            R.id.radio_volk -> {
                songVerse = getString(R.string.volk_verse, t1, t2)
                songChorus = getString(R.string.volk_chorus, t3)
            }
            else -> {
                songVerse = getString(R.string.genre_error)
            }
        }
        songString = createSongString(songVerse, songChorus, songLength.toInt())
        sV.text = songString
    }
}
