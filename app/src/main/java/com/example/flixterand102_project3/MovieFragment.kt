package com.example.flixterand102_project3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONObject


private const val API_KEY = "1752564ca5a3252561658a9621b19a2d" //a07e22bc18f5cb106bfe4cc1f83ad8ed
class MovieFragment: Fragment(), OnListFragmentInteractionListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceSate: Bundle?
    ): View?{


        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = LinearLayoutManager(context)

        updateAdapter(progressBar, recyclerView)

        return view


    }

    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()


        //create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api-key"] = API_KEY

        client[
            "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed",
            params, object : JsonHttpResponseHandler() {
                // Using the client, perform the HTTP request
                override fun onSuccess(
                    statusCode: Int,
                    headers: Headers?,
                    json: JsonHttpResponseHandler.JSON?

                ) {
                    // The wait for a response is over
                    progressBar.hide()

                    //TODO - Parse JSON into Models
                    val resultJson = json?.jsonObject?.get("results").toString()
                    val gson = Gson()
                    val arrayMovieType = object: TypeToken<List<Movie>>(){}.type
                    val models: List<Movie> = gson.fromJson(resultJson, arrayMovieType)
                    recyclerView.adapter = MovieRecyclerViewAdapter(models)

                    // Look for this in Logcat:
                    Log.d("BestSellerBooksFragment", "response successful")
                }

                /*
                 * The onFailure function gets called when
                 * HTTP response status is "4XX" (eg. 401, 403, 404)
                 */
                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    t: Throwable?
                ) {
                    // The wait for a response is over
                    progressBar.hide()

                    // If the error is not null, log it!
                    t?.message?.let {
                        Log.e("MovieFragment", errorResponse)
                    }
                }
            }]

    }


    override fun onItemClick(item: Movie) {
        Toast.makeText(context, "test: " + item.movieTitle, Toast.LENGTH_LONG).show()

    }



}