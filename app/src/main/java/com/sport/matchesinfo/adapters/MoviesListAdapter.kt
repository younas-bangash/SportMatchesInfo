package com.sport.matchesinfo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sport.matchesinfo.data.MatchDetails
import com.sport.matchesinfo.databinding.ListItemMoviesBinding
import com.sport.matchesinfo.view.MatchesListFragmentDirections

/**
 * Adapter for the [RecyclerView] in MatchesListFragment
 */
class MoviesListAdapter(private val list: ArrayList<MatchDetails>) :
    RecyclerView.Adapter<MoviesListAdapter.MatchesListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesListViewHolder {
        return MatchesListViewHolder(
            ListItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MatchesListViewHolder, position: Int) {
        val item = list.get(position)
        holder.bind(item)
    }

    class MatchesListViewHolder(private val binding: ListItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(matchDetails: MatchDetails) {

            binding.apply {
                item = matchDetails
                executePendingBindings()
            }

            binding.root.setOnClickListener { view ->
                val direction =
                    MatchesListFragmentDirections.actionMatchesListFragmentToMatchDetailsFragment(
                        matchDetails
                    )
                view.findNavController().navigate(direction)
            }
        }
    }

    internal fun updateData(newList: List<MatchDetails>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}