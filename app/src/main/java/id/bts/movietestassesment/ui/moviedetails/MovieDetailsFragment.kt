package id.bts.movietestassesment.ui.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import id.bts.movietestassesment.R
import id.bts.movietestassesment.base.BaseFragment
import id.bts.movietestassesment.databinding.FragmentMovieDetailsBinding
import id.bts.movietestassesment.ui.adapters.MovieDetailVideoListAdapter
import id.bts.movietestassesment.ui.adapters.MovieReviewListAdapter
import id.bts.movietestassesment.utils.Constants

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding, MovieDetailsViewModel>() {

    private var movieId: Long = 0
    private var page: Int = 1
    private var isLastItem: Boolean = false

    private lateinit var videoAdapter: MovieDetailVideoListAdapter
    private lateinit var reviewAdapter: MovieReviewListAdapter

    override fun setLayout(inflater: LayoutInflater): FragmentMovieDetailsBinding {
        return FragmentMovieDetailsBinding.inflate(inflater)
    }

    override fun setViewModel(): MovieDetailsViewModel {
        return ViewModelProvider(this)[MovieDetailsViewModel::class.java]
    }

    override fun setupView() {
        arguments?.let {
            movieId = it.getLong("movie_id")
        }
        setupRecyclerView()
        getMovieData()
    }

    private fun setupRecyclerView() {
        setupVideoRecyclerView()
        setupReviewRecyclerView()
    }

    private fun getMovieData() {
        getMovieDetails()
        getMovieVideos()
        getMovieReviews()
    }

    private fun setupVideoRecyclerView() {
        videoAdapter = MovieDetailVideoListAdapter(arrayListOf())
        binding.rvMovieVideos.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = videoAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupReviewRecyclerView() {
        reviewAdapter = MovieReviewListAdapter(arrayListOf())
        binding.rvMovieReviews.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = reviewAdapter
            setHasFixedSize(true)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1) && !isLastItem) {
                        page++
                        getMovieReviews()
                    }
                }
            })
        }
    }

    private fun getMovieDetails() {
        viewModel.getMovieDetails(movieId).observe(this){ data ->
            val genres = data.genres.joinToString(separator = ", ") {
                it.name
            }
            val posterPath = "${Constants.BASE_URL_POSTER}${data.posterPath}"
            val backdropPath = "${Constants.BASE_URL_BACKDROP}${data.backdropPath}"

            binding.tvMovieTitle.text = data.title
            binding.tvMovieOverview.text = data.overview
            binding.tvMovieScore.text = data.voteAverage.toString()
            binding.tvMovieGenre.text = genres
            binding.tvMovieReleaseDate.text = formatDate(data.releaseDate)
            binding.ivMovieBackdrop.load(backdropPath)
            binding.ivMoviePoster.load(posterPath)
        }
    }

    private fun getMovieVideos() {
        viewModel.getMovieVideos(movieId).observe(this){ data ->
            if(data.results.count() > 0){
                binding.rvMovieVideos.visibility = View.VISIBLE
                binding.tvEmptyVideo.visibility = View.GONE
            }
            data.results.let { videoAdapter.setData(it) }
        }
    }

    private fun getMovieReviews() {
        if (isLastItem) return

        viewModel.getMovieReviews(movieId, page).observe(this) { data ->

            if(data.results.count() > 0){
                binding.rvMovieReviews.visibility = View.VISIBLE
                binding.tvEmptyReview.visibility = View.GONE
            }

            if (page > 1) {
                data.results.let { reviewAdapter.addData(it) }
            } else {
                data.results.let { reviewAdapter.setData(it) }
            }

            isLastItem = page == data.totalPages
        }
    }

}