package com.collaborator.android


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_issues_list.*

/**
 * A simple [Fragment] subclass.
 */
class IssuesListFragment : Fragment() {

    // Access a Cloud Firestore instance from your Activity
    private val db = FirebaseFirestore.getInstance()
    private val issuesList = mutableListOf<IssuesModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_issues_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = db.collection("issues")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    issuesList.add(convertToIssueModel(document))
                }
//                addNewEntry(result.documents[0].data!!)
                issues_recycler_view.adapter = IssuesAdapter(issuesList)
            }
            .addOnFailureListener { exception ->
                Log.w("Main", "Error getting documents.", exception)
            }
    }

    private fun addNewEntry(map: Map<String, Any>) {
        db.collection("issues")
            .add(map)
    }

}
