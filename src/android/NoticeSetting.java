
package cordova.plugin.NoticeSetting;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

/**
 * This class echoes a string called from JavaScript.
 */
public class NoticeSetting extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("systemNoticeSetting")) {
            String message = args.getString(0);
            this.systemNoticeSetting(message, callbackContext);
            return true;
        }
        return false;
    }

    private void systemNoticeSetting(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            System.out.println("======================123");

            try {
                // 根据isOpened结果，判断是否需要提醒用户跳转AppInfo页面，去打开App通知权限
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                //这种方案适用于 API 26, 即8.0（含8.0）以上可以用
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, this.cordova.getActivity().getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, this.cordova.getActivity().getApplicationInfo().uid);

                //这种方案适用于 API21——25，即 5.0——7.1 之间的版本可以使用
                intent.putExtra("app_package", this.cordova.getActivity().getPackageName());
                intent.putExtra("app_uid", this.cordova.getActivity().getApplicationInfo().uid);

                // 小米6 -MIUI9.6-8.0.0系统，是个特例，通知设置界面只能控制"允许使用通知圆点"——然而这个玩意并没有卵用，我想对雷布斯说：I'm not ok!!!
                //  if ("MI 6".equals(Build.MODEL)) {
                //      intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                //      Uri uri = Uri.fromParts("package", getPackageName(), null);
                //      intent.setData(uri);
                //      // intent.setAction("com.android.settings/.SubSettings");
                //  }
                cordova.getActivity().startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                // 出现异常则跳转到应用设置界面：锤子坚果3——OC105 API25
                Intent intent = new Intent();

                //下面这种方案是直接跳转到当前应用的设置界面。
                //https://blog.csdn.net/ysy950803/article/details/71910806
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", this.cordova.getActivity().getPackageName(), null);
                intent.setData(uri);
                cordova.getActivity().startActivity(intent);
            }
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
