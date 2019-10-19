package com.collaborator.android

import com.google.firebase.firestore.QueryDocumentSnapshot

data class IssuesModel(
    val assignee: String,
    val issueDays: Long,
    val imageUrl: List<String>,
    val userName: String,
    val upVotes: Long,
    val downVotes: Long,
    val locality: String,
    val location: Pair<Double, Double>,
    val issueDetail: String,
    val issuesTitle: String,
    val status: IssuesStatus,
    val date: Long
) {
    enum class IssuesStatus (val status: Int) {
        STATUS_PENDING (0),
        STATUS_STARTED (1),
        STATUS_COMPLETED (2)
    }
}

fun convertToIssueModel(data: QueryDocumentSnapshot): IssuesModel {
    return data.run {
        IssuesModel(
            assignee = get("assignee") as String,
            issueDays = get("issue_days_tracker") as Long,
            imageUrl = get("image_url") as List<String>,
            userName = getString("user_name") ?: "",
            upVotes = get("up_votes") as Long,
            downVotes = get("down_votes") as Long,
            locality = get("locality") as String,
            location = getGeoPoint("location")?.let { it.latitude to it.longitude } ?: 0.0 to 0.0,
            issueDetail = get("detail") as String,
            issuesTitle = get("title") as String,
            status = when((get("status") as Long).toInt()) {
                0 -> IssuesModel.IssuesStatus.STATUS_PENDING
                1 -> IssuesModel.IssuesStatus.STATUS_STARTED
                2 -> IssuesModel.IssuesStatus.STATUS_COMPLETED
                else -> IssuesModel.IssuesStatus.STATUS_PENDING
            },
            date = getTimestamp("date")?.toDate()?.time ?: 0
        )
    }

}