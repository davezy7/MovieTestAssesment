package id.bts.movietestassesment.ui.discoverbygenre

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import id.bts.movietestassesment.base.BaseActivity
import id.bts.movietestassesment.databinding.ActivityDiscoverByGenreBinding
import id.bts.movietestassesment.ui.adapters.DiscoverByGenreListAdapter

@AndroidEntryPoint
class DiscoverByGenreActivity : BaseActivity<ActivityDiscoverByGenreBinding, DiscoverByGenreViewModel>() {

    private val TAG = DiscoverByGenreActivity::class.java.simpleName

    private lateinit var genreName: String
    private lateinit var discoverByGenreListAdapter: DiscoverByGenreListAdapter


    private var genreId: Int = 0
    private var page: Int  = 1
    private var isLastItem: Boolean = false
    private var isLoadingNewData: Boolean = false

    override fun setLayout(inflater: LayoutInflater): ActivityDiscoverByGenreBinding {
        return ActivityDiscoverByGenreBinding.inflate(inflater)
    }

    override fun setViewModel(): DiscoverByGenreViewModel {
        return ViewModelProvider(this)[DiscoverByGenreViewModel::class.java]
    }

    override fun setupView() {
        genreId = intent.getIntExtra("GENRE_ID", 0)
        genreName = intent.getStringExtra("GENRE_NAME")!!
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        showLoadingDialog()
        getAllMoviesByGenre()
    }

    private fun setupRecyclerView(){
        discoverByGenreListAdapter = DiscoverByGenreListAdapter()
        val linearLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        binding.rvListMovie.apply {
            layoutManager = linearLayoutManager
            adapter = discoverByGenreListAdapter
            setHasFixedSize(true)
            addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if(!recyclerView.canScrollVertically(1) && !isLastItem && !isLoadingNewData){
                        isLoadingNewData = true
                        page++
                        getAllMoviesByGenre()
                        isLoadingNewData = false
                    }
                }
            })
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getAllMoviesByGenre(){
        if(isLastItem) return

        viewModel.getAllMoviesByGenre(genreId, page)
        viewModel.movieResponse.observe(this){ response ->
            if(response.isSuccessful && response.body() != null){
                binding.tvGenreName.text = genreName
                Log.d(TAG, "Success Fetch Data: ${response.body()!!.results}")
                if(page > 1){
                    response.body()!!.results.let { discoverByGenreListAdapter.addData(it) }
                } else {
                    response.body()!!.results.let { discoverByGenreListAdapter.setData(it) }
                }
                binding.rvListMovie.post{
                    kotlin.run {
                        discoverByGenreListAdapter.notifyDataSetChanged()
                    }
                }
                isLastItem = page == response.body()!!.totalPages

            } else {
                showToast("Failed Fetch Data: ${response.errorBody()!!.string()}")

                Log.d(TAG, "Failed Fetch Data: ${response.errorBody()!!.string()}")
            }
            hideLoadingDialog()
        }
    }
}