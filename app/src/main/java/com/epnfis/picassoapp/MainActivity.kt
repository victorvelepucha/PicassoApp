package com.epnfis.picassoapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var animalAdapter: AnimalAdapter
    private lateinit var animals: Array<String>
    private lateinit var layoutManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        animals = getAnimalsLinks()
        animalAdapter = AnimalAdapter(this, animals, R.layout.image_layout)
        layoutManager = GridLayoutManager(this, 2)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = animalAdapter
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
            R.id.action_LinksUrl -> {
                recyclerView.adapter = animalAdapter
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    fun getAnimalsLinks(): Array<String> {
        return arrayOf(
            "https://static.pexels.com/photos/86462/red-kite-bird-of-prey-milan-raptor-86462.jpeg",
            "https://static.pexels.com/photos/67508/pexels-photo-67508.jpeg",
            "https://static.pexels.com/photos/55814/leo-animal-savannah-lioness-55814.jpeg",
            "https://static.pexels.com/photos/40745/red-squirrel-rodent-nature-wildlife-40745.jpeg",
            "https://static.pexels.com/photos/33392/portrait-bird-nature-wild.jpg",
            "https://static.pexels.com/photos/62640/pexels-photo-62640.jpeg",
            "https://static.pexels.com/photos/38438/rattlesnake-toxic-snake-dangerous-38438.jpeg",
            "https://static.pexels.com/photos/33149/lemur-ring-tailed-lemur-primate-mammal.jpg",
            "https://static.pexels.com/photos/1137/wood-animal-dog-pet.jpg",
            "https://static.pexels.com/photos/40731/ladybug-drop-of-water-rain-leaf-40731.jpeg",
            "https://static.pexels.com/photos/40860/spider-macro-insect-arachnid-40860.jpeg",
            "https://static.pexels.com/photos/63282/crab-yellow-ocypode-quadrata-atlantic-ghost-crab-63282.jpeg",
            "https://static.pexels.com/photos/45246/green-tree-python-python-tree-python-green-45246.jpeg",
            "https://static.pexels.com/photos/39245/zebra-stripes-black-and-white-zoo-39245.jpeg",
            "https://static.pexels.com/photos/92000/pexels-photo-92000.jpeg",
            "https://static.pexels.com/photos/121445/pexels-photo-121445.jpeg",
            "https://static.pexels.com/photos/112603/pexels-photo-112603.jpeg",
            "https://static.pexels.com/photos/52961/antelope-nature-flowers-meadow-52961.jpeg",
            "https://static.pexels.com/photos/36450/flamingo-bird-pink-nature.jpg",
            "https://static.pexels.com/photos/20861/pexels-photo.jpg",
            "https://static.pexels.com/photos/54108/peacock-bird-spring-animal-54108.jpeg",
            "https://static.pexels.com/photos/24208/pexels-photo-24208.jpg"
        )
    }
}
