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

    /**
     * diese Funktion gibt den Wert des ausgewählten RadioButtons zurück
     **/
    fun getGenreFromRadios(): String {
        try {
            var genre = findViewById<RadioButton>(findViewById<RadioGroup>(R.id.music_type_radio).checkedRadioButtonId).text.toString()
            return genre
        } catch (ex: Exception) {
            return ex.toString()
        }
    }

    /**
     * diese Funktion gibt den Wert des Sliders zurück
     **/
    fun getSongLength(): Float {
        var songLength = findViewById<Slider>(R.id.song_text_slider).value
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
        var t1 = getStringFromInput(R.id.firstText) // Eingabe aus Feld 1
        var t2 = getStringFromInput(R.id.secondText) // Eingabe aus Feld 2
        var t3 = getStringFromInput(R.id.thirdText) // Eingabe aus Feld 3
        var genre = getGenreFromRadios() // Auswahl des Genre
        var songLength = getSongLength() // AUswahl Song Länge
        var songString: String = "" // Variable wird für die zusammengesetzte Lyrik verwendet
        var sV = findViewById<TextView>(R.id.song_text_tv) // TextView in dem der Song angezeigt wird
        sV.setMovementMethod(ScrollingMovementMethod()) // Macht TextView scrollbar
        var songVerse: String = "" // Strophen des jeweiligen Songs in dieser Variable speichern
        var songChorus: String = "" // Refrain des jewiligen Songs in dieser Variable speichern

        when (genre) {
            "Rap" -> {
                songVerse = getString(R.string.rap_verse, t1, t2, t3)
                songChorus = getString(R.string.rap_chorus)
            }
            "Pop" -> {
                songVerse = getString(R.string.pop_verse, t1, t2, t3)
                songChorus = getString(R.string.pop_chorus)
            }
            "Folk", "Volk" -> {
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
