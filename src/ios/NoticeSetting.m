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
    NSString *identifier = [[NSBundle mainBundle] bundleIdentifier];
    NSURL *openUrl = [NSURL URLWithString:[NSString stringWithFormat:@"App-Prefs:root=NOTIFICATIONS_ID&path=%@",identifier]];
    if ([[UIApplication sharedApplication] canOpenURL:openUrl]){
      //在iOS应用程序中打开设备设置界面及其中某指定的选项界面-通知界面
      [[UIApplication sharedApplication] openURL:openUrl];
      pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@""];
    }else{
    	pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
