package cn.pengxun.vshop.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.vzan.geetionlib.base.BaseApplication;

/**
 * @author liu-feng
 * @date 2017/2/22 0022.
 * Email:w710989327@foxmail.com
 */
public class SystemUtil {

    // 获取当前应用的版本号
    public static String getVersionName() {
        try {
            PackageManager packageManager = BaseApplication.getContext().getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(BaseApplication.getContext()
                    .getPackageName(), 0);
            String version = packInfo.versionName;
            if (!TextUtils.isEmpty(version)) {
                return "V" + version;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "V1.0";
    }

    // 获取当前应用的版本号
    public static int getVersionCode() {
        try {
            PackageManager packageManager = BaseApplication.getContext().getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(BaseApplication.getContext()
                    .getPackageName(), 0);
            return packInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
}
