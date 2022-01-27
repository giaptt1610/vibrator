#import "VibratorPlugin.h"
#if __has_include(<vibrator/vibrator-Swift.h>)
#import <vibrator/vibrator-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "vibrator-Swift.h"
#endif

@implementation VibratorPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftVibratorPlugin registerWithRegistrar:registrar];
}
@end
