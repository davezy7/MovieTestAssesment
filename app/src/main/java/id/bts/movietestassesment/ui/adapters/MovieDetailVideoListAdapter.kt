package id.bts.movietestassesment.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import id.bts.movietestassesment.data.dto.MovieVideosResultResponse
import id.bts.movietestassesment.databinding.ItemListVideosBinding

class MovieDetailVideoListAdapter(
    private val videoList: ArrayList<MovieVideosResultResponse>
): RecyclerView.Adapter<MovieDetailVideoListAdapter.MovieDetailVideoListAdapterVH>() {

    class MovieDetailVideoListAdapterVH(val bind: ItemListVideosBinding): RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieDetailVideoListAdapterVH {
        val v = ItemListVideosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieDetailVideoListAdapterVH(v)
    }

    override fun onBindViewHolder(holder: MovieDetailVideoListAdapterVH, position: Int) {
        val video = videoList[position]


        holder.bind.vvItemVideo.addYouTubePlayerListener(object: AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.cueVideo(video.key, 0F)
            }
        })
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<MovieVideosResultResponse>){
        videoList.clear()
        videoList.addAll(data)
        notifyDataSetChanged()
    }
}