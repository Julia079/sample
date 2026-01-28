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

    private val nickname: String by lazy {
        val userProfilePrefs = context.getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        userProfilePrefs.getString("NICKNAME", "") ?: ""
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.skill_item, parent, false)
        return SkillViewHolder(view)
    }

    override fun onBindViewHolder(holder: SkillViewHolder, position: Int) {
        val skill = skills[position]
        holder.skillName.text = skill.name
        holder.skillDescription.text = skill.description

        val unlockPrefs = context.getSharedPreferences("TopicUnlockStatus", Context.MODE_PRIVATE)
        val isUnlocked = unlockPrefs.getBoolean("${nickname}_${skill.name}", false)

        val isLocked = position != 0 && !isUnlocked

        if (skill.name == "Final Assessment") {
            val assessmentPrefs = context.getSharedPreferences("AssessmentStatus", Context.MODE_PRIVATE)
            val hasTakenAssessment = assessmentPrefs.getBoolean("${nickname}_hasTakenAssessment", false)

            if (hasTakenAssessment) {
                holder.itemView.alpha = 0.5f
                holder.starIcon.visibility = View.VISIBLE
                holder.lockIcon.visibility = View.GONE
                holder.itemView.setOnClickListener {
                    Toast.makeText(context, "You have already completed the final assessment.", Toast.LENGTH_SHORT).show()
                }
            } else {
                holder.itemView.alpha = 1.0f
                holder.starIcon.visibility = View.GONE
                holder.lockIcon.visibility = if (isLocked) View.VISIBLE else View.GONE
                holder.itemView.setOnClickListener {
                    if (isLocked) {
                        val previousSkillName = skills.getOrNull(position - 1)?.name ?: "the previous topic"
                        Toast.makeText(context, "Complete '$previousSkillName' to unlock.", Toast.LENGTH_SHORT).show()
                    } else {
                        val intent = Intent(context, EndingDialogActivity::class.java)
                        context.startActivity(intent)
                    }
                }
            }
        } else {
            if (isLocked) {
                holder.itemView.alpha = 0.5f
                holder.lockIcon.visibility = View.VISIBLE
                holder.starIcon.visibility = View.GONE
                holder.itemView.setOnClickListener {
                    val previousSkillName = skills.getOrNull(position - 1)?.name ?: "the previous topic"
                    Toast.makeText(context, "Complete '$previousSkillName' to unlock.", Toast.LENGTH_SHORT).show()
                }
            } else {
                holder.itemView.alpha = 1.0f
                holder.lockIcon.visibility = View.GONE
                holder.starIcon.visibility = View.GONE
                holder.itemView.setOnClickListener {
                    val intent = Intent(context, SkillActivity::class.java).apply {
                        putExtra("SKILL_NAME", skill.name)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount() = skills.size

    class SkillViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val skillName: TextView = itemView.findViewById(R.id.skill_name)
        val skillDescription: TextView = itemView.findViewById(R.id.skill_description)
        val lockIcon: ImageView = itemView.findViewById(R.id.lock_icon)
        val starIcon: ImageView = itemView.findViewById(R.id.star_icon)
    }
}
