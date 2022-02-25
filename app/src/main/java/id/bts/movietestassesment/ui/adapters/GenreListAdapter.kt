package id.bts.movietestassesment.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import id.bts.movietestassesment.data.dto.GenreResponse
import id.bts.movietestassesment.databinding.ItemListGenresBinding
import id.bts.movietestassesment.ui.genrelist.GenreListFragmentDirections

class GenreListAdapter(
    private val genreList: ArrayList<GenreResponse>
) : RecyclerView.Adapter<GenreListAdapter.GenreListAdapterVH>() {

    private val TAG: String = GenreListAdapter::class.java.simpleName

    class GenreListAdapterVH(val bind: ItemListGenresBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreListAdapterVH {
        val v = ItemListGenresBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreListAdapterVH(v)
    }

    override fun onBindViewHolder(holder: GenreListAdapterVH, position: Int) {
        val genre = genreList[position]

        holder.bind.tvGenreName.text = genre.name

        holder.bind.cvGenreList.setOnClickListener { v ->
            val action = GenreListFragmentDirections
                .actionGenreListFragmentToDiscoverByGenreFragment(genreId = genre.id, genreName = genre.name)
            v.findNavController().navigate(action)
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<GenreResponse>) {
        genreList.clear()
        genreList.addAll(data)
        notifyDataSetChanged()
    }


}