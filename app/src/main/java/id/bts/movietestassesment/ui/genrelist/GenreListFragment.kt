package id.bts.movietestassesment.ui.genrelist

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.bts.movietestassesment.base.BaseFragment
import id.bts.movietestassesment.databinding.FragmentGenreListBinding
import id.bts.movietestassesment.ui.adapters.GenreListAdapter
import id.bts.movietestassesment.ui.main.MainViewModel

@AndroidEntryPoint
class GenreListFragment : BaseFragment<FragmentGenreListBinding, MainViewModel>(){

    private lateinit var genreListAdapter: GenreListAdapter

    override fun setLayout(inflater: LayoutInflater): FragmentGenreListBinding {
        return FragmentGenreListBinding.inflate(inflater)
    }

    override fun setViewModel(): MainViewModel {
        return ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun setupView() {
        setupRecyclerView()
        getAllMovieGenres()
    }

    private fun setupRecyclerView() {
        genreListAdapter = GenreListAdapter(arrayListOf())
        binding.rvListGenre.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = genreListAdapter
            setHasFixedSize(true)
        }
    }

    private fun getAllMovieGenres() {
        viewModel.getAllMovieGenres().observe(this) { data ->
            genreListAdapter.setData(data.genres)
        }
    }

}