package com.rodrigmatrix.protonweather.internal

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

fun <T> Task<T>.asDefered(): Deferred<T> {
    val deferred = CompletableDeferred<T>()
    this.addOnSuccessListener {
        deferred.complete(it)
    }

    this.addOnFailureListener {
        deferred.completeExceptionally(it)
    }

    return deferred
}