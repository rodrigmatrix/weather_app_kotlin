package com.rodrigmatrix.protonweather.internal

import kotlinx.coroutines.*

fun <T> lazyDefered(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>>{
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}