package id.bts.movietestassesment.ui.main

import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.bts.movietestassesment.base.BaseActivity
import id.bts.movietestassesment.databinding.ActivityMainBinding
import id.bts.movietestassesment.ui.adapters.GenreListAdapter

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var genreListAdapter: GenreListAdapter

    override fun setLayout(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun setViewModel(): MainViewModel {
        return ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun setupView() {
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        showLoadingDialog()
        getAllMovieGenres()

    }

    private fun setupRecyclerView(){
        genreListAdapter = GenreListAdapter(arrayListOf())
        binding.rvListGenre.apply {
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            adapter = genreListAdapter
            setHasFixedSize(true)
        }
    }

    private fun getAllMovieGenres(){
        viewModel.getAllMovieGenres()
        viewModel.genreResponse.observe(this) { response ->
            if (response.isSuccessful && response.body() != null) {
                response.body()!!.genres.let { genreListAdapter.setData(it) }

                Log.d(TAG, "Success Fetch Data: ${response.body()!!.genres}")
            } else {
                showToast("Failed Fetch Data: ${response.errorBody()!!.string()}")

                Log.d(TAG, "Failed Fetch Data: ${response.errorBody()!!.string()}")
            }
            hideLoadingDialog()
        }
    }

}