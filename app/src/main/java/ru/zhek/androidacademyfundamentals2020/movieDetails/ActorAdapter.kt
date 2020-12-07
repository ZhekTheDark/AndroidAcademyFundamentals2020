package ru.zhek.androidacademyfundamentals2020.movieDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.zhek.androidacademyfundamentals2020.R
import ru.zhek.androidacademyfundamentals2020.data.models.Actor
import ru.zhek.androidacademyfundamentals2020.databinding.ViewHolderActorBinding

class ActorAdapter(private val actors: List<Actor>) :
    RecyclerView.Adapter<ActorAdapter.ActorViewHolder>() {

    class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ViewHolderActorBinding.bind(itemView)

        fun bind(actor: Actor) {
            Glide.with(this.itemView.context)
                .load(actor.image)
                .centerCrop()
                .into(binding.ivActor)
            binding.ivActor.contentDescription = actor.name
            binding.tvActor.text = actor.name
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
}
