package id.bts.movietestassesment.ui.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.bts.movietestassesment.data.dto.DiscoverByGenreResultResponse
import id.bts.movietestassesment.databinding.ItemListMoviesBinding
import id.bts.movietestassesment.utils.Constants
import java.text.SimpleDateFormat

class DiscoverByGenreListAdapter(
    private val movieList: ArrayList<DiscoverByGenreResultResponse>
) : RecyclerView.Adapter<DiscoverByGenreListAdapter.DiscoverByGenreListAdapterVH>() {


    class DiscoverByGenreListAdapterVH(val bind: ItemListMoviesBinding) :
        RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DiscoverByGenreListAdapterVH {
        val v = ItemListMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiscoverByGenreListAdapterVH(v)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: DiscoverByGenreListAdapterVH, position: Int) {
        val movie = movieList[position]

        var sdf = SimpleDateFormat("yyyy-MM-dd")
        val releaseDate = sdf.parse(movie.releaseDate)
        sdf = SimpleDateFormat("dd MMM yyyy")
        val dateText = sdf.format(releaseDate!!)

        val posterUrl = "${Constants.BASE_URL_POSTER}${movie.posterPath}"

        holder.bind.tvMovieTitle.text = movie.title
        holder.bind.tvReleaseDate.text = dateText
        holder.bind.tvVoteAverage.text = movie.voteAverage.toString()
        holder.bind.ivMoviePoster.load(posterUrl)

    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setData(data: List<DiscoverByGenreResultResponse>){
        movieList.clear()
        movieList.addAll(data)
        notifyDataSetChanged()
    }
}