package com.eloquence.verbconjugator.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eloquence.verbconjugator.R
import com.eloquence.verbconjugator.model.Verb

class VerbAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<VerbAdapter.VerbViewHolder>() {

    private var verbs: List<Verb> = emptyList()
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    inner class VerbViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvInfinitive: TextView = itemView.findViewById(R.id.infinitive_view)
        var tvMeta: TextView = itemView.findViewById(R.id.meta_view)
        var tvTranslation: TextView = itemView.findViewById(R.id.translation_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerbViewHolder {
        val itemView = layoutInflater.inflate(R.layout.verbview_holder, parent, false)
        return VerbViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return verbs.size
    }

    override fun onBindViewHolder(holder: VerbViewHolder, position: Int) {
        val currentVerb = verbs[position]
        holder.tvInfinitive.text = currentVerb.infinitivePresent
        holder.tvMeta.text =
            "${currentVerb.verbclass} ${currentVerb.reflexivity} ${currentVerb.separability}"
        holder.tvTranslation.text = "Translation"
    }

    internal fun setVerbs(verbs: List<Verb>) {
        this.verbs = verbs
        notifyDataSetChanged()
    }
}