package com.raccoon.fakeindicator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MonitorActivity : AppCompatActivity() {

    @Inject
    lateinit var model: BusinessModel

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitor)
    }

    override fun onStart() {
        super.onStart()
        disposable.add(
                model.climate().subscribe {
                    findViewById<TextView>(R.id.text_temperature).text = resources.getString(R.string.temperature_f, it.temperature)
                    findViewById<TextView>(R.id.text_humidity).text = resources.getString(R.string.humidity_percentage, it.humidity)
                    findViewById<TextView>(R.id.text_pressure).text = resources.getString(R.string.air_pressure_mm, it.pressure)
                })
    }

    override fun onStop() {
        if (disposable.isDisposed) {
            disposable.dispose()
        }
        super.onStop()
    }
}
