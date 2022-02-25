package id.bts.movietestassesment.ui.discoverbygenre

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import id.bts.movietestassesment.R
import id.bts.movietestassesment.base.BaseFragment
import id.bts.movietestassesment.databinding.FragmentDiscoverByGenreBinding
import id.bts.movietestassesment.ui.adapters.DiscoverByGenreListAdapter

@AndroidEntryPoint
class DiscoverByGenreFragment : BaseFragment<FragmentDiscoverByGenreBinding, DiscoverByGenreViewModel>() {


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
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1) && !isLastItem) {
                        page++
                        getAllMoviesByGenre()
                    }
                }
            })
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getAllMoviesByGenre() {
        if (isLastItem) return
        viewModel.getAllMoviesByGenre(genreId, page).observe(this) { data ->

            binding.tvGenreName.text = genreName
            if (page > 1) {
                data.results.let { discoverByGenreListAdapter.addData(it) }
            } else {
                data.results.let { discoverByGenreListAdapter.setData(it) }
            }
            isLastItem = page == data.totalPages
        }
    }
}