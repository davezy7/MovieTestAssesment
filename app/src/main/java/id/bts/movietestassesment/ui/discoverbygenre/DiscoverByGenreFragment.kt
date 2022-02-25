package id.bts.movietestassesment.ui.discoverbygenre

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import id.bts.movietestassesment.R
import id.bts.movietestassesment.base.BaseFragment
import id.bts.movietestassesment.databinding.FragmentDiscoverByGenreBinding
import id.bts.movietestassesment.ui.adapters.DiscoverByGenreListAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DiscoverByGenreFragment : BaseFragment<FragmentDiscoverByGenreBinding, DiscoverByGenreViewModel>() {

    private val TAG = DiscoverByGenreFragment::class.java.simpleName

    private lateinit var genreName: String
    private lateinit var discoverByGenreListAdapter: DiscoverByGenreListAdapter

    private var genreId: Int = 0
    private var page: Int = 1
    private var isLastItem: Boolean = false

    override fun setLayout(inflater: LayoutInflater): FragmentDiscoverByGenreBinding {
        return FragmentDiscoverByGenreBinding.inflate(inflater)
    }

    override fun setViewModel(): DiscoverByGenreViewModel {
        return ViewModelProvider(this)[DiscoverByGenreViewModel::class.java]
    }

    override fun setupView() {
        arguments?.let {
            genreId = it.getInt("genre_id")
            genreName = it.getString("genre_name").toString()
        }
        setupRecyclerView()
        getAllMoviesByGenre()
    }

    private fun setupRecyclerView() {
        discoverByGenreListAdapter = DiscoverByGenreListAdapter()
        binding.rvListMovie.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = discoverByGenreListAdapter
            setHasFixedSize(true)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getAllMoviesByGenre() {
        if (isLastItem) return
        lifecycleScope.launch{
            viewModel.getAllMoviesByGenre(genreId).collectLatest { pagingData ->
                discoverByGenreListAdapter.submitData(pagingData)
                Log.d(TAG, pagingData.toString())
            }
        }
    }
}