package com.example.sample

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sample.models.Skill
import com.example.sample.TopicSummaryActivity

class SummaryAdapter(private val skills: List<Skill>, private val context: Context) : RecyclerView.Adapter<SummaryAdapter.SummaryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.skill_item, parent, false)
        return SummaryViewHolder(view)
    }

    override fun onBindViewHolder(holder: SummaryViewHolder, position: Int) {
        val skill = skills[position]
        holder.skillName.text = skill.name
        holder.skillDescription.text = skill.description

        // *** CRITICAL FIX: Explicitly hide the lock icon ***
        holder.lockIcon.visibility = View.GONE

        holder.itemView.setOnClickListener {
            val intent = Intent(context, TopicSummaryActivity::class.java)
            intent.putExtra("SKILL_NAME", skill.name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = skills.size

    class SummaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val skillName: TextView = itemView.findViewById(R.id.skill_name)
        val skillDescription: TextView = itemView.findViewById(R.id.skill_description)
        // *** CRITICAL FIX: Add a reference to the lock icon ***
        val lockIcon: ImageView = itemView.findViewById(R.id.lock_icon)
    }
}
