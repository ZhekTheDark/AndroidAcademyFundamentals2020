package ru.zhek.androidacademyfundamentals2020.movieDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.zhek.androidacademyfundamentals2020.data.Actor
//import ru.zhek.androidacademyfundamentals2020.data.models.Actor
import ru.zhek.androidacademyfundamentals2020.databinding.ViewHolderActorBinding

// TODO
/*class ActorAdapter(private val actors: List<Actor>) :
    RecyclerView.Adapter<ActorAdapter.ActorViewHolder>() {

    class ActorViewHolder(private var binding: ViewHolderActorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(actor: Actor) {
            binding.apply {
                Glide.with(root)
                    .load(actor.image)
                    .centerCrop()
                    .into(ivActor)
                ivActor.contentDescription = actor.name
                tvActor.text = actor.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(
            ViewHolderActorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount(): Int {
        return actors.size
    }
}*/

class ActorAdapter(private val actors: List<Actor>) :
    RecyclerView.Adapter<ActorAdapter.ActorViewHolder>() {

    class ActorViewHolder(private var binding: ViewHolderActorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(actor: Actor) {
            binding.apply {
                Glide.with(root)
                    .load(actor.picture)
                    .centerCrop()
                    .into(ivActor)
                ivActor.contentDescription = actor.name
                tvActor.text = actor.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(
            ViewHolderActorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount(): Int {
        return actors.size
    }
}
