package com.example.myapplication

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

private const val CHARACTER_DATA_KEY = "CHARACTER_DATA_KEY"
private var Bundle.characterData
    get() = getSerializable(CHARACTER_DATA_KEY) as CharacterGenerator.CharacterData
    set(value) = putSerializable(CHARACTER_DATA_KEY, value)

class MainActivity : AppCompatActivity() {

    private var characterData = CharacterGenerator.generate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

//        シリアライズされて保存してあるものがなければ、新しくデータを取得する
//        characterData = savedInstanceState?.let {
//            it.getSerializable(CHARACTER_DATA_KEY) as CharacterGenerator.CharacterData
//        } ?: CharacterGenerator.generate()
        characterData = savedInstanceState?.characterData?:
            CharacterGenerator.generate()
        displayCharacterData()

        generateButton.setOnClickListener {
            characterData = CharacterGenerator.generate()
            displayCharacterData()
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
//        outState?.putSerializable(CHARACTER_DATA_KEY, characterData)
        outState?.characterData = characterData
    }

    private fun displayCharacterData() =
        characterData.run {
            nameTextView.text = name
            raceTextView.text = race
            dexterityTextView.text = dex
            wisdomTextView.text = wis
            strengthTextView.text = str
        }
}
