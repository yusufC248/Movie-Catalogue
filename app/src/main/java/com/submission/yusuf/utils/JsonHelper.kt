package com.submission.yusuf.utils

import android.content.Context
import com.submission.yusuf.data.source.remote.response.ContentResponse
import com.submission.yusuf.data.source.remote.response.FilmResponse
import com.submission.yusuf.data.source.remote.response.ModuleResponse
import com.submission.yusuf.data.source.remote.response.TvsResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {
    private fun parsingFileToString(fileName: String): String?{
        return try{
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (ex: IOException){
            ex.printStackTrace()
            null
        }
    }

    fun loadMovies(): List<FilmResponse>{
        val list = ArrayList<FilmResponse>()
        try{
            val responseObject = JSONObject(parsingFileToString("CatalogueFilmJos.json").toString())
            val listArray = responseObject.getJSONArray("movies")
            for(i in 0 until listArray.length()){
                val movie = listArray.getJSONObject(i)

                val filmId = movie.getString("filmId")
                val title = movie.getString("title")
                val description = movie.getString("description")
                val imagePath = movie.getString("imagePath")
                val background = movie.getString("background")
                val rating = movie.getString("rating")

                val movieResponse = FilmResponse(filmId, title, description, imagePath, background, rating)
                list.add(movieResponse)
            }
        }catch (e: JSONException){
            e.printStackTrace()
        }
        return list
    }


    fun loadTvShow(): List<TvsResponse>{
        val list = ArrayList<TvsResponse>()
        try{
            val responseObject = JSONObject(parsingFileToString("CatalogueFilmJos.json").toString())
            val listArray = responseObject.getJSONArray("tvshows")
            for(i in 0 until listArray.length()){
                val movie = listArray.getJSONObject(i)

                val filmId = movie.getString("filmId")
                val title = movie.getString("title")
                val description = movie.getString("description")
                val imagePath = movie.getString("imagePath")
                val background = movie.getString("background")
                val rating = movie.getString("rating")

                val tvshowResponse = TvsResponse(filmId, title, description, imagePath, background, rating)
                list.add(tvshowResponse)
            }
        }catch (e: JSONException){
            e.printStackTrace()
        }
        return list
    }

    fun loadModule(courseId: String): List<ModuleResponse> {
        val fileName = String.format("Module_%s.json", courseId)
        val list = ArrayList<ModuleResponse>()
        try {
            val result = parsingFileToString(fileName)
            if (result != null) {
                val responseObject = JSONObject(result)
                val listArray = responseObject.getJSONArray("modules")
                for (i in 0 until listArray.length()) {
                    val course = listArray.getJSONObject(i)

                    val moduleId = course.getString("moduleId")
                    val title = course.getString("title")
                    val position = course.getString("position")

                    val courseResponse = ModuleResponse(moduleId, courseId, title, Integer.parseInt(position))
                    list.add(courseResponse)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadContent(moduleId: String): ContentResponse {
        val fileName = String.format("Content_%s.json", moduleId)
        var contentResponse: ContentResponse? = null
        try {
            val result = parsingFileToString(fileName)
            if (result != null) {
                val responseObject = JSONObject(result)
                val content = responseObject.getString("content")
                contentResponse = ContentResponse(moduleId, content)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return contentResponse as ContentResponse
    }
}