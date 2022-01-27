import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:vibrator/vibrator.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              ElevatedButton(
                  onPressed: () async {
                    await Vibrator.turnVibrateOn();
                  },
                  child: Text('Start Vibrate')),
              SizedBox(height: 10.0),
              ElevatedButton(
                  onPressed: () async {
                    await Vibrator.turnVibrateOff();
                  },
                  child: Text('Stop Vibrate')),
            ],
          ),
        ),
      ),
    );
  }
}
