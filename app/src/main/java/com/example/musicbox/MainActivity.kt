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
        // diese Funktion soll den Wert des ausgewählten RadioButtons zurückgeben
        try {
            var genre = findViewById<RadioButton>(findViewById<RadioGroup>(R.id.music_type_radio).checkedRadioButtonId).text.toString()
            return genre
        } catch (ex: Exception) {
            return ex.toString()
        }
    }

    fun getSongLength(): Float {
        // diese Funktion soll den Wert des Sliders zurückgeben
        var songLength = findViewById<Slider>(R.id.song_text_slider).value
        return songLength
    }

    fun getStringFromInput(tvId: Int): String {
        // Diese Funktion soll die EditText Felder auslesen und den Wert zurückgeben
        var str = findViewById<EditText>(tvId).text.toString()
        return str
    }

    fun createSongString(genreVerse: String, genreRefrain: String, songLength: Int): String {
        // Diese Funktion soll Verse und Refrain zu einem String zusammenführen und je nach songLength Wiederholen
        return ((genreVerse) + genreRefrain.repeat(3)).repeat(songLength)
    }

    fun createSongText() {
        // Die Funktion soll die vorher implementierten Funktionen aufrufen, um die Infos asuzulesen und zusammen zuführen
        // Außerdem soll der erstellte Song in dem TextView unterhalb des Formulars erscheinen
        var t1 = getStringFromInput(R.id.firstText) // Eingabe aus Feld 1
        var t2 = getStringFromInput(R.id.secondText) // Eingabe aus Feld 2
        var t3 = getStringFromInput(R.id.thirdText) // Eingabe aus Feld 3
        var genre = getGenreFromRadios() // Auswahl des Genre
        var songLength = getSongLength() // AUswahl Song Länge
        var songString: String = "" // Variable wird für die zusammengesetzte Lyrik verwendet
        var sV = findViewById<TextView>(R.id.song_text_tv) // TextView in dem der Song angezeigt wird
        sV.setMovementMethod(ScrollingMovementMethod()) // Macht TextView scrollbar
        var songVerse: String = "" // Strophen des jeweiligen Songs in dieser Variable speichern
        var songRefrain: String = "" // Refrain des jewiligen Songs in dieser Variable speichern

        when (genre) {
            "Rap" -> {
                // Formatiere die Rap Strings hier
                songVerse = getString(R.string.rap_song, t1, t2, t3)
                songRefrain = getString(R.string.rap_song_ref)
            }
            "Pop" -> {
                // Formatiere die Pop Strings hier
                songVerse = getString(R.string.pop_song, t1, t2, t3)
                songRefrain = getString(R.string.pop_ref)
            }
            "Volk" -> {
                // Formatiere die Volk Strings hier
                songVerse = getString(R.string.volk_song, t1, t2)
                songRefrain = getString(R.string.volk_song_ref, t3)
            }
            else -> {
                songVerse = getString(R.string.genre_error)
            }
        }
        songString = createSongString(songVerse, songRefrain, songLength.toInt())
        sV.text = songString
    }
}
