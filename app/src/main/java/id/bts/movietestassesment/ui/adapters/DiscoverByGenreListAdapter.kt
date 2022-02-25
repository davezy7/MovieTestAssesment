package id.bts.movietestassesment.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.bts.movietestassesment.data.dto.DiscoverByGenreResultResponse
import id.bts.movietestassesment.databinding.ItemListMoviesBinding
import id.bts.movietestassesment.ui.discoverbygenre.DiscoverByGenreFragmentDirections
import id.bts.movietestassesment.utils.Constants
import java.text.SimpleDateFormat

class DiscoverByGenreListAdapter :
    PagingDataAdapter<
            DiscoverByGenreResultResponse,
            DiscoverByGenreListAdapter.DiscoverByGenreListAdapterVH>(MovieComparator) {

    private val movieList: ArrayList<DiscoverByGenreResultResponse> = arrayListOf()

    object MovieComparator : DiffUtil.ItemCallback<DiscoverByGenreResultResponse>(){
        override fun areItemsTheSame(
            oldItem: DiscoverByGenreResultResponse,
            newItem: DiscoverByGenreResultResponse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DiscoverByGenreResultResponse,
            newItem: DiscoverByGenreResultResponse
        ): Boolean {
            return oldItem == newItem
        }

    }

    class DiscoverByGenreListAdapterVH(val bind: ItemListMoviesBinding) :
        RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DiscoverByGenreListAdapterVH {
        val v = ItemListMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiscoverByGenreListAdapterVH(v)
    }


    override fun onBindViewHolder(holder: DiscoverByGenreListAdapterVH, position: Int) {
        val movie = movieList[position]
        val posterUrl = "${Constants.BASE_URL_POSTER}${movie.posterPath}"

        holder.bind.tvMovieTitle.text = movie.title
        holder.bind.tvReleaseDate.text = formatDate(movie.releaseDate)
        holder.bind.tvVoteAverage.text = movie.voteAverage.toString()
        holder.bind.ivMoviePoster.load(posterUrl)

        holder.bind.cvMovieResult.setOnClickListener { v ->
            val action = DiscoverByGenreFragmentDirections
                .actionDiscoverByGenreFragmentToMovieDetailsFragment(movie.id)
            v.findNavController().navigate(action)
            notifyItemChanged(position)
        }

    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatDate(dateStr: String) : String{
        var sdf = SimpleDateFormat("yyyy-MM-dd")
        val releaseDate = sdf.parse(dateStr)
        sdf = SimpleDateFormat("dd MMM yyyy")
        return sdf.format(releaseDate)
    }
}