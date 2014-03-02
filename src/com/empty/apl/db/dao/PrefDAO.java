package com.empty.apl.db.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.empty.framework.db.dao.BasePrefDAO;


/**
 * アプリのプリファレンスを扱うクラス。
 * @author id:language_and_engineering
 *
 */
public class PrefDAO extends BasePrefDAO
{
    // ここにプリファレンスの読み書き処理を記述。

    private static String SENSITIVITY_KEY = "sensitivity";
    /**
     * 振動センサー感度を更新
     */
    public void updateSensitivity(Context context, int sensitivity)
    {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putInt(SENSITIVITY_KEY, sensitivity);
        editor.commit();

        Log.d("preference", "振動センサー感度を変更：" + sensitivity);
    }

    /**
     * 振動センサー感度を取得
     */
    public int getSensitivity(Context context)
    {
        SharedPreferences settings = getSettings(context);
        int ret = settings.getInt(SENSITIVITY_KEY, 500);
        Log.d("preference", "振動センサー感度を取得：" + ret);
        return ret;
    }

}
