package com.wangkm.jetcaster.data.room

import androidx.room.Dao
import androidx.room.Ignore
import androidx.room.Transaction

/**
 * @author: created by wangkm
 * @time: 2022/07/07 12:28
 * @desc：
 * @email: 1240413544@qq.com
 */

@Dao
abstract class TransactionRunnerDao : TransactionRunner {
    @Transaction
    protected open suspend fun runInTransaction(tx: suspend () -> Unit) = tx()

    @Ignore
    override suspend fun invoke(tx: suspend () -> Unit) {
        runInTransaction(tx)
    }
}

/**
 * Interface with operator function which will invoke the suspending lambda within a database
 * transaction.
 */
interface TransactionRunner {
    suspend operator fun invoke(tx: suspend () -> Unit)
}

