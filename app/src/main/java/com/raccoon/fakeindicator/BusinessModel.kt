package com.raccoon.fakeindicator

import android.util.Log
import com.raccoon.fakeindicator.data.ClimateData
import com.raccoon.fakeindicator.provider.DataProvider
import com.raccoon.fakeindicator.schedulers.ISchedulers
import io.reactivex.Observable
import io.reactivex.Observable.combineLatest
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import java.util.concurrent.TimeUnit

class BusinessModel(private val provider: DataProvider, private val scheduler: ISchedulers) {

    private val tag = javaClass.simpleName

    fun climate(): Observable<ClimateData> =
            Observable.interval(100, TimeUnit.MILLISECONDS)
                    .withLatestFrom(combine(),
                            BiFunction<Long, Triple<Int, Int, Int>, ClimateData> { _, triple -> ClimateData(triple.first, triple.second, triple.third) })
                    .doOnError { Log.e(tag, it.localizedMessage) }
                    .doOnNext { Log.d(tag, it.toString()) }
                    .subscribeOn(scheduler.io())
                    .observeOn(scheduler.main())

    private fun combine() =
            combineLatest(
                    provider.temperature(), provider.humidity(), provider.pressure(),
                    Function3<Int, Int, Int, Triple<Int, Int, Int>> { t, h, p -> Triple(t, h, p) })

}
