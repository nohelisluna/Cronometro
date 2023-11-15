package com.example.cronometro1

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.cronometro1.databinding.ActivityTemporizadorBinding

class Temporizador : AppCompatActivity() {
    lateinit var binding: ActivityTemporizadorBinding
    lateinit var tiempo: CountDownTimer
    lateinit var editTextHours: EditText
    lateinit var editTextMinutes: EditText
    lateinit var editTextSeconds: EditText
    lateinit var tvTimeRemaining: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTemporizadorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        editTextHours = findViewById(R.id.texthora)
        editTextMinutes = findViewById(R.id.textMinuto)
        editTextSeconds = findViewById(R.id.textSegundo)
        tvTimeRemaining = findViewById(R.id.tvtiempo)
        binding.btndetener.setOnClickListener { detener() }
    }

    private fun detener() {
        tiempo.cancel()
        binding.btndetener.isEnabled= false
        binding.button.isEnabled = true
    }

    fun startTimer(view: View) {
        binding.button.isEnabled = false
        binding.btndetener.isEnabled= true
        val hours = editTextHours.text.toString().toLongOrNull() ?: 0
        val minutes = editTextMinutes.text.toString().toLongOrNull() ?: 0
        val seconds = editTextSeconds.text.toString().toLongOrNull() ?: 0

        val totalTimeMillis = (hours * 3600 + minutes * 60 + seconds) * 1000

        tiempo = object : CountDownTimer(totalTimeMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val remainingHours = millisUntilFinished / 3600000
                val remainingMinutes = (millisUntilFinished % 3600000) / 60000
                val remainingSeconds = (millisUntilFinished % 60000) / 1000

                val formattedTime = String.format(
                    "%02d:%02d:%02d",
                    remainingHours, remainingMinutes, remainingSeconds
                )

                tvTimeRemaining.text = formattedTime
            }

            override fun onFinish() {
                tvTimeRemaining.text = "00:00:00"
                stop()
            }
        }
        tiempo.start()
    }
    fun stop(){
        var mp = MediaPlayer.create(this, R.raw.sonido)
        mp.start()
    }
}