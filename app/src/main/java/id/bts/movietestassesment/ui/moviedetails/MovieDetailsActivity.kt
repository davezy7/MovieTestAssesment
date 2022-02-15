package id.bts.movietestassesment.ui.moviedetails

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import id.bts.movietestassesment.base.BaseActivity
import id.bts.movietestassesment.databinding.ActivityMovieDetailsBinding
import id.bts.movietestassesment.ui.adapters.MovieDetailVideoListAdapter
import id.bts.movietestassesment.ui.adapters.MovieReviewListAdapter
import id.bts.movietestassesment.utils.Constants
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsActivity : BaseActivity<ActivityMovieDetailsBinding, MovieDetailsViewModel>() {

    private val TAG = MovieDetailsActivity::class.java.simpleName
    private var movieId: Long = 0
    private var page: Int = 1
    private var isLastItem: Boolean = false
    private var isLoadingNewData: Boolean = false

    private lateinit var videoAdapter: MovieDetailVideoListAdapter
    private lateinit var reviewAdapter: MovieReviewListAdapter

    override fun setLayout(inflater: LayoutInflater): ActivityMovieDetailsBinding {
        return ActivityMovieDetailsBinding.inflate(inflater)
    }

    override fun setViewModel(): MovieDetailsViewModel {
        return ViewModelProvider(this)[MovieDetailsViewModel::class.java]
    }

    override fun setupView() {
        movieId = intent.getLongExtra("MOVIE_ID", 0)
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        showLoadingDialog()
        getMovieData()
    }

    private fun setupRecyclerView(){
        setupVideoRecyclerView()
        setupReviewRecyclerView()
    }

    private fun getMovieData(){
        getMovieDetails()
        getMovieVideos()
        getMovieReviews()
    }

    private fun setupVideoRecyclerView(){
        videoAdapter = MovieDetailVideoListAdapter(arrayListOf())
        binding.rvMovieVideos.apply{
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            adapter = videoAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupReviewRecyclerView(){
        reviewAdapter = MovieReviewListAdapter(arrayListOf())
        binding.rvMovieReviews.apply {
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            adapter = reviewAdapter
            setHasFixedSize(true)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1) && !isLastItem && !isLoadingNewData) {
                        isLoadingNewData = true
                        page++
                        getMovieReviews()
                        isLoadingNewData = false
                    }
                }
            })
        }
    }

    private fun getMovieDetails() {
        viewModel.getMovieDetails(movieId)
        viewModel.movieDetailResponse.observe(this) { response ->
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!
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
            hideLoadingDialog()
        }
    }

    private fun getMovieVideos(){
        viewModel.getMovieVideos(movieId)
        viewModel.movieVideoResponse.observe(this){ response ->
            if(response.isSuccessful && response.body() != null){
                if(response.body()!!.results.count() > 0){
                    binding.rvMovieVideos.visibility = View.VISIBLE
                    binding.tvEmptyVideo.visibility = View.GONE
                }
                response.body()!!.results.let { videoAdapter.setData(it) }
            }
        }
    }

    private fun getMovieReviews(){
        if(isLastItem) return

        viewModel.getMovieReviews(movieId, page)
        viewModel.movieReviewResponse.observe(this){ response ->
            if(response.isSuccessful && response.body() != null){
                if(response.body()!!.results.count() > 0){
                    binding.rvMovieReviews.visibility = View.VISIBLE
                    binding.tvEmptyReview.visibility = View.GONE
                }
                response.body()!!.results.let { reviewAdapter.setData(it) }
                isLastItem = page == response.body()!!.totalPages
            }
        }
    }
}