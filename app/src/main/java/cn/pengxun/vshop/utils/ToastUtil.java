package cn.pengxun.vshop.utils;

import android.text.TextUtils;
import android.widget.Toast;

import com.vzan.geetionlib.base.BaseApplication;

/**
 * @author liu-feng
 * @date 2017/2/22 0022.
 * Email:w710989327@foxmail.com
 */
public class ToastUtil {

    private static Toast mToast;

    public static void show(String message) {
        if (TextUtils.isEmpty(message)) return;
        int duration;
        if (message.length() > 10) {
            duration = Toast.LENGTH_LONG; //如果字符串比较长，那么显示的时间也长一些。
        } else {
            duration = Toast.LENGTH_SHORT;
        }
        if (mToast == null) {
            mToast = Toast.makeText(BaseApplication.getContext(), message, duration);
        } else {
            mToast.setText(message);
            mToast.setDuration(duration);
        }
        mToast.show();
    }
}
