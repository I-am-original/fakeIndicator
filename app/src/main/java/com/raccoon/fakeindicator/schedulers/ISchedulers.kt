package com.raccoon.fakeindicator.schedulers

import io.reactivex.Scheduler

/**
 * Interface for Implementing proper schedulers for work and testing
 */
interface ISchedulers {

    fun io(): Scheduler

    fun main(): Scheduler

}