package com.moyehics.movieapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.moyehics.movieapp.R
import com.moyehics.movieapp.data.room.entities.Movie
import com.moyehics.movieapp.data.room.entities.MovieCategory
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel:MovieViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(!getFirstTimeBool()) {
           getDataFromJSON()
        }
    }


    // parsing data from JSON and insert data into room database


    private fun getDataFromJSON(){
        val categories = loadJSONFromAssets()
        if (categories != null) {
            try {
                for (i in 0 until categories.length()) {
                    val cat = categories.getJSONObject(i)
                    val catName = cat.getString("name")
                    viewModel.insertMovieCategory(
                        MovieCategory(
                            movieCategoryName = catName
                        )
                    )
                    val moviesJsonArrayProperty: JSONArray = cat.getJSONArray("movies")
                    for (j in 0 until moviesJsonArrayProperty.length()) {
                        val movie = moviesJsonArrayProperty.getJSONObject(j)
                        val movieName = movie.getString("name")
                        val movieDesc = movie.getString("description")
                        viewModel.insertMovie(
                            Movie(
                                name = movieName,
                                description = movieDesc,
                                movieCategoryName = catName
                            )
                        )
                    }
                }
                saveFirstTime()
            } catch (e: JSONException) {
                e.printStackTrace()
                cantLoadJSONFile()
            } catch (e: Exception) {
                e.printStackTrace()
                cantLoadJSONFile()
            }
        }
    }


    // get JSON File From Assets
    private fun loadJSONFromAssets(): JSONArray? {
        val builder=StringBuilder()
        val inputStream:InputStream=assets.open("movies-data.json")
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line: String?
        try {
            line=reader.readLine()
            while (line!=null){
                builder.append(line)
                line=reader.readLine()
            }
            val json=JSONObject(builder.toString())
            return json.getJSONArray("categories")
        }catch (e:JSONException){
            e.printStackTrace()
            cantLoadJSONFile()
        }catch (e:IOException){
            e.printStackTrace()
            cantLoadJSONFile()
        }
        cantLoadJSONFile()
        return null
    }


    private fun cantLoadJSONFile(){
        Toast.makeText(this,"Can't load Json File",Toast.LENGTH_LONG).show()
    }

    // mark user that first time enter to app
    // indicate that json file added to room data base
    private fun saveFirstTime(){
        val sharedPref = this.getPreferences(MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("firstTime", true)
            commit()
        }
    }
    private fun getFirstTimeBool(): Boolean {
        val sharedPref = getPreferences(MODE_PRIVATE)
        return sharedPref.getBoolean("firstTime", false)
    }

}