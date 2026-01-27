package com.example.sample

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.sample.models.Skill

class SkillAdapter(private val skills: List<Skill>, private val context: Context) : RecyclerView.Adapter<SkillAdapter.SkillViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.skill_item, parent, false)
        return SkillViewHolder(view)
    }

    override fun onBindViewHolder(holder: SkillViewHolder, position: Int) {
        val skill = skills[position]
        holder.skillName.text = skill.name
        holder.skillDescription.text = skill.description

        val unlockPrefs = context.getSharedPreferences("TopicUnlockStatus", Context.MODE_PRIVATE)
        val isUnlocked = unlockPrefs.getBoolean(skill.name, false)

        val isLocked = position != 0 && !isUnlocked

        if (isLocked) {
            holder.itemView.alpha = 0.5f
            holder.lockIcon.visibility = View.VISIBLE
            holder.itemView.setOnClickListener {
                val previousSkillName = skills.getOrNull(position - 1)?.name ?: "the previous topic"
                Toast.makeText(context, "Complete '$previousSkillName' to unlock.", Toast.LENGTH_SHORT).show()
            }
        } else {
            holder.itemView.alpha = 1.0f
            holder.lockIcon.visibility = View.GONE
            holder.itemView.setOnClickListener {
                val intent = if (skill.name == "Final Assessment") {
                    Intent(context, EndingDialogActivity::class.java)
                } else {
                    Intent(context, SkillActivity::class.java).apply {
                        putExtra("SKILL_NAME", skill.name)
                    }
                }
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount() = skills.size

    class SkillViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val skillName: TextView = itemView.findViewById(R.id.skill_name)
        val skillDescription: TextView = itemView.findViewById(R.id.skill_description)
        val lockIcon: ImageView = itemView.findViewById(R.id.lock_icon)
    }
}
