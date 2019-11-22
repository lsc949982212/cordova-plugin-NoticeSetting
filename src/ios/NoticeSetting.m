/********* RBNoticeSeting.m Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>

@interface NoticeSetting : CDVPlugin {
  // Member variables go here.
}

- (void)systemNoticeSetting:(CDVInvokedUrlCommand*)command;
@end

@implementation NoticeSetting

- (void)systemNoticeSetting:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSURL *openUrl = [NSURL URLWithString:UIApplicationOpenSettingsURLString];
    if ([[UIApplication sharedApplication] canOpenURL:openUrl]){
        if ([[[UIDevice currentDevice] systemVersion] floatValue] >= 10.0)
        {
            [[UIApplication sharedApplication] openURL:[NSURL URLWithString:UIApplicationOpenSettingsURLString] options:@{} completionHandler:nil];
        }else{
            [[UIApplication sharedApplication] openURL:[NSURL URLWithString:UIApplicationOpenSettingsURLString]];
        }
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@""];
    }else{
      pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
