package com.dasonick.rollsapplication.model

import com.dasonick.rollsapplication.base.Roll
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Repository {
    private val myDB = FirebaseFirestore.getInstance()

    suspend fun getRolls(): List<Roll>? {
        return suspendCoroutine { continuation ->
            myDB.collection("Rolls")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val rolls = ArrayList<Roll>()
                    for (document in querySnapshot.documents) {
                        rolls.add(Roll(document.get("title") as String, document.get("imageUrl") as String))
                    }
                    continuation.resume(rolls)
                }
                .addOnFailureListener {
                    continuation.resume(null)
                }
        }
    }
}