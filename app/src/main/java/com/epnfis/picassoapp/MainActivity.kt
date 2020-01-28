package com.epnfis.picassoapp

import android.Manifest
import android.content.ContentUris
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var animalAdapter: AnimalAdapter
    private lateinit var partyAdapter: PartyAdapter
    private lateinit var imagesAdapter: ImagesAdapter

    private lateinit var animals: Array<String>
    private lateinit var parties: IntArray
    private lateinit var images: MutableList<String> //ArrayList<String>

    private val PERMISSION_READ_EXTERNAL_MEMORY = 1

    private lateinit var layoutManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        animals = getAnimalsLinks()
        parties = getPartyPics()
        images = getImagesPath()

        animalAdapter = AnimalAdapter(this, animals, R.layout.image_layout)
        partyAdapter = PartyAdapter(this, parties, R.layout.image_layout)
        imagesAdapter = ImagesAdapter(this, images, R.layout.image_layout)

        layoutManager = GridLayoutManager(this, 2)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = animalAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_LinksUrl -> {
                recyclerView.adapter = animalAdapter
                true
            }
            R.id.action_LinksRecursos -> {
                recyclerView.adapter = partyAdapter
                true
            }
            R.id.action_LinksMemoriaExterna -> {
                checkForPermission()
                images.clear()
                images.addAll(getImagesPath())
                recyclerView.adapter = imagesAdapter
                imagesAdapter.notifyDataSetChanged()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun checkForPermission() {
        val permissionCheck = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                PERMISSION_READ_EXTERNAL_MEMORY
            )
        }
    }

    private fun hasPermission(permissionToCheck: String): Boolean {
        val permissionCheck = ContextCompat.checkSelfPermission(this, permissionToCheck)
        return permissionCheck == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_READ_EXTERNAL_MEMORY -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    images.clear()
                    images.addAll(getImagesPath())
                    imagesAdapter.notifyDataSetChanged()
                }
            }
        }
    }


    private fun getAnimalsLinks(): Array<String> {
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

    private fun getPartyPics(): IntArray {
        return intArrayOf(
            R.drawable.ballons,
            R.drawable.christmas,
            R.drawable.concert,
            R.drawable.drinks,
            R.drawable.fiction,
            R.drawable.fire,
            R.drawable.glass,
            R.drawable.fireworks,
            R.drawable.glass,
            R.drawable.guy,
            R.drawable.notice,
            R.drawable.olives
        )
    }

    private fun getImagesPath(): MutableList<String> {
        val listOfAllImages: MutableList<String> = ArrayList()
        if (hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            val projection = arrayOf(
                MediaStore.Images.Media._ID
            )

            val query = contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                MediaStore.Images.Media._ID
            )
            query?.use { cursor ->
                // Cache column indices.
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val contentUri: Uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                    listOfAllImages.add(contentUri.toString())
                }
            }
        }
        return listOfAllImages
    }
}
