package com.raccoon.fakeindicator.provider

import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Single responsibility class for providing data
 */
class DataProvider {

    fun temperature(): Observable<Int> =
            Observable.interval(randomInterval(), TimeUnit.MILLISECONDS)
                    .map { random(5).plus(65) }

    fun humidity(): Observable<Int> =
            Observable.interval(randomInterval(), TimeUnit.MILLISECONDS)
                    .map { random(5).plus(25) }

    fun pressure(): Observable<Int> =
            Observable.interval(randomInterval(), TimeUnit.MILLISECONDS)
                    .map { random(10).plus(760) }

    private fun random(bound: Int) = Random().nextInt(bound)

    private fun randomInterval() = random(100).plus(100).toLong()


}