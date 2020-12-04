package ru.zhek.androidacademyfundamentals2020.movieDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.zhek.androidacademyfundamentals2020.R
import ru.zhek.androidacademyfundamentals2020.data.models.Actor

class ActorAdapter : RecyclerView.Adapter<ActorAdapter.ActorViewHolder>() {

    private var actors = listOf<Actor>()

    class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val actorPhoto = itemView.findViewById<ImageView>(R.id.iv_actor)
        private val actorName = itemView.findViewById<TextView>(R.id.tv_actor)

        fun bind(actor: Actor) {
            Glide.with(this.itemView.context)
                .load(actor.image)
                .centerCrop()
                .into(actorPhoto)
            actorPhoto.contentDescription = actor.name
            actorName.text = actor.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount(): Int {
        return actors.size
    }

    fun bindActors(newActors: List<Actor>) {
        actors = newActors
        notifyDataSetChanged()
    }
}
