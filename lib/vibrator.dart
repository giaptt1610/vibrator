import 'dart:async';

import 'package:flutter/services.dart';

class Vibrator {
  static const MethodChannel _channel =
      const MethodChannel('com.hust1610.vibrator/vibration');

  static Future<bool> turnVibrateOff() async {
    try {
      final result = (await _channel.invokeMethod('turnOffVibrate')) as bool;
      return result;
    } catch (e) {
      print('Flutter::turnVibrate, exception: ${e.toString()}');
      return false;
    }
  }

  static Future<bool> turnVibrateOn() async {
    try {
      final result = (await _channel.invokeMethod('turnOnVibrate')) as bool;
      return result;
    } catch (e) {
      print('Flutter::turnVibrate, exception: ${e.toString()}');
      return false;
    }
  }
}
