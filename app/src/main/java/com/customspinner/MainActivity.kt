package com.customspinner

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.searchablespinnerlibrary.showSearchablePopupAbove

class MainActivity : AppCompatActivity() {

    var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val alphabetWords = arrayListOf(
            "A", "Apple", "Ant", "Anchor",
            "B", "Ball", "Bat", "Book",
            "C", "Cat", "Car", "Cake",
            "D", "Dog", "Drum", "Duck",
            "E", "Elephant", "Egg", "Engine",
            "F", "Fish", "Fan", "Frog",
            "G", "Goat", "Grapes", "Gift",
            "H", "Horse", "House", "Hat",
            "I", "Ice", "Igloo", "Iron",
            "J", "Jug", "Jelly", "Jeep",
            "K", "Kite", "King", "Key",
            "L", "Lion", "Lamp", "Leaf",
            "M", "Monkey", "Moon", "Milk",
            "N", "Nest", "Net", "Nose",
            "O", "Owl", "Orange", "Ocean",
            "P", "Parrot", "Pen", "Pencil",
            "Q", "Queen", "Quilt", "Quail",
            "R", "Rabbit", "Rain", "Ring",
            "S", "Snake", "Star", "Sun",
            "T", "Tiger", "Tree", "Table",
            "U", "Umbrella", "Unicorn", "Urchin",
            "V", "Van", "Vase", "Violin",
            "W", "Whale", "Water", "Window",
            "X", "Xylophone", "X-ray", "Xenon",
            "Y", "Yak", "Yarn", "Yellow",
            "Z", "Zebra", "Zoo", "Zero"
        )
        textView = findViewById<TextView>(R.id.tvText)

        textView?.setOnClickListener {

            it.showSearchablePopupAbove(alphabetWords) {
                textView?.text = it
            }
        }

    }
}