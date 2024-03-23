package com.example.flixterand102_project3


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class MovieRecyclerViewAdapter(
    private val movies: List<Movie>,

)
    : RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>()
    {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MovieViewHolder {

            val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_movie, parent, false)
            return MovieViewHolder(view)
        }

        inner class MovieViewHolder(val mView: View): RecyclerView.ViewHolder(mView) {
            var mItem: Movie? = null

            val mMovieTitle: TextView = mView.findViewById<View>(R.id.movie_title) as TextView
            val mMoviePoster: ImageView = mView.findViewById<View>(R.id.movie_poster) as ImageView
            val mMovieDescription: TextView = mView.findViewById<View>(R.id.movie_description) as TextView

        }

        override fun onBindViewHolder(
            holder: MovieViewHolder,
            position: Int
        ) {
            val movie = movies[position]

            holder.mItem = movie
            holder.mMovieTitle.text = movie.movieTitle
            holder.mMovieDescription.text = movie.overview

            val fullImageUrl = "https://image.tmdb.org/t/p/w500/" + movie.moviePosterImageUrl

            Glide.with(holder.mView)
                .load(fullImageUrl)
                .centerInside()
                .into(holder.mMoviePoster)
        }

        override fun getItemCount(): Int {
            return movies.size
        }

    }