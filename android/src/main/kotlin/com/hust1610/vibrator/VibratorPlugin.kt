package com.hust1610.vibrator

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** VibratorPlugin */
class VibratorPlugin : FlutterPlugin, MethodCallHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel
    private val CHANNEL = "com.hust1610.vibrator/vibration"

    private lateinit var vibrator: Vibrator
    private val pattern: LongArray = longArrayOf(0, 100, 200, 300, 400)
    private val vibrationEffect =
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            VibrationEffect.createWaveform(pattern, 2)
        } else {
            TODO("VERSION.SDK_INT < O")
        }

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, CHANNEL)
        channel.setMethodCallHandler(this)
        vibrator =
            flutterPluginBinding.applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        when (call.method) {
            "turnOnVibrate" -> {
                val success: Boolean = turnOnVibrate()
                result.success(success)
            }
            "turnOffVibrate" -> {
                turnOffVibrate()
                result.success(true)
            }
            else -> result.notImplemented()
        }
    }

    private fun turnOnVibrate(): Boolean {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            vibrator.vibrate(vibrationEffect)
            return true
        }
        return false
    }

    private fun turnOffVibrate() {
        vibrator.cancel()
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}
