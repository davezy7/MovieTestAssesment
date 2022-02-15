package id.bts.movietestassesment.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import id.bts.movietestassesment.R
import id.bts.movietestassesment.data.dto.MovieReviewResultResponse
import id.bts.movietestassesment.databinding.ItemListReviewsBinding
import id.bts.movietestassesment.utils.Constants

class MovieReviewListAdapter(
    private val reviewList: ArrayList<MovieReviewResultResponse>
) : RecyclerView.Adapter<MovieReviewListAdapter.MovieReviewListAdapterVH>() {

    class MovieReviewListAdapterVH(val bind: ItemListReviewsBinding) :
        RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewListAdapterVH {
        val v = ItemListReviewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieReviewListAdapterVH(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MovieReviewListAdapterVH, position: Int) {
        val review = reviewList[position]

        // Load Image
        if (review.authorDetails.avatarPath != null) {
            val userAvatarUrl: String =
                if (review.authorDetails.avatarPath.contains("gravatar")) review.authorDetails.avatarPath.substring(
                    1
                ) // If avatar is from Gravatar's site
                else "${Constants.BASE_URL_POSTER}${review.authorDetails.avatarPath}" // if avatar is from TMDB's site

            holder.bind.ivUserAvatar.load(userAvatarUrl) {
                transformations(CircleCropTransformation())
            }
        } else {
            holder.bind.ivUserAvatar.load(R.drawable.ic_avatar_placeholder)
        }

        // Get User Rating
        val userRatingText = "Rating:"
        if (review.authorDetails.rating != null) {
            holder.bind.tvUserRating.text = "$userRatingText ${review.authorDetails.rating}"
        } else {
            holder.bind.tvUserRating.text = "$userRatingText -"
        }

        holder.bind.tvUserName.text = review.author
        holder.bind.tvReviewContent.text = review.content
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<MovieReviewResultResponse>) {
        reviewList.clear()
        reviewList.addAll(data)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addData(data: List<MovieReviewResultResponse>) {
        reviewList.addAll(data)
        notifyDataSetChanged()
    }

}