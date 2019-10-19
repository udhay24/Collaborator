package com.collaborator.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.issues_view_holder.view.*

class IssuesAdapter(
    private val issuesList: List<IssuesModel>
): RecyclerView.Adapter<IssuesAdapter.IssueViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.issues_view_holder, parent, false)
        return IssueViewHolder(view)
    }

    override fun getItemCount(): Int = issuesList.size

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        holder.bindToViewHolder(issuesList[position])
    }

    inner class IssueViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        fun bindToViewHolder(issuesModel: IssuesModel) {
            view.issue_title.text = issuesModel.issuesTitle
            view.issue_description.text = issuesModel.issueDetail
            Picasso.get()
                .load(issuesModel.imageUrl[0])
                .into(view.issue_image)
            view.upvotes.text = issuesModel.upVotes.toString()
        }
    }
}