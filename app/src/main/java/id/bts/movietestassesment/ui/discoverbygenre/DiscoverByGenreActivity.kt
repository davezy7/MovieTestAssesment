package id.bts.movietestassesment.ui.discoverbygenre

import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.bts.movietestassesment.base.BaseActivity
import id.bts.movietestassesment.databinding.ActivityDiscoverByGenreBinding
import id.bts.movietestassesment.ui.adapters.DiscoverByGenreListAdapter

@AndroidEntryPoint
class DiscoverByGenreActivity : BaseActivity<ActivityDiscoverByGenreBinding, DiscoverByGenreViewModel>() {

    private val TAG = DiscoverByGenreActivity::class.java.simpleName
    private var genreId: Int = 0
    private lateinit var genreName: String
    private lateinit var discoverByGenreListAdapter: DiscoverByGenreListAdapter

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
        discoverByGenreListAdapter = DiscoverByGenreListAdapter(arrayListOf())
        binding.rvListMovie.apply {
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            adapter = discoverByGenreListAdapter
            setHasFixedSize(true)
        }
    }

    private fun getAllMoviesByGenre(){
        viewModel.getAllMoviesByGenre(genreId)
        viewModel.movieResponse.observe(this){ response ->
            if(response.isSuccessful && response.body() != null){
                response.body()!!.results.let { discoverByGenreListAdapter.setData(it) }
                binding.tvGenreName.text = genreName

                Log.d(TAG, "Success Fetch Data: ${response.body()!!.results}")
            } else {
                showToast("Failed Fetch Data: ${response.errorBody()!!.string()}")

                Log.d(TAG, "Failed Fetch Data: ${response.errorBody()!!.string()}")
            }
            hideLoadingDialog()
        }
    }

}