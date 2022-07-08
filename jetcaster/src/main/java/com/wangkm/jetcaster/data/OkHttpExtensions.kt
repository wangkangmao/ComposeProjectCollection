package com.wangkm.jetcaster.data

import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import okhttp3.internal.closeQuietly
import java.io.IOException
import kotlin.coroutines.resumeWithException

/**
 * @author: created by wangkm
 * @time: 2022/07/07 12:13
 * @desc：
 * @email: 1240413544@qq.com
 */

suspend fun Call.await(): Response = suspendCancellableCoroutine { cancellableContinuation ->

    enqueue(
        object : Callback {

            override fun onResponse(call: Call, response: Response) {
                cancellableContinuation.resume(response) {
                    if (response.body != null) {
                        response.closeQuietly()
                    }
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                cancellableContinuation.resumeWithException(e)
            }

        }
    )

    cancellableContinuation.invokeOnCancellation {
        try {
            cancel()
        } catch (t: Throwable) {
            // Ignore cancel exception
        }
    }

}

