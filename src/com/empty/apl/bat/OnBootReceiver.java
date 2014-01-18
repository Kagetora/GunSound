package com.empty.apl.bat;

import android.content.Context;

import com.empty.apl.common.AppSettings;
import com.empty.framework.bat.BaseOnBootReceiver;
import com.empty.framework.common.FWUtil;

/**
 * 端末起動時の処理。
 * @author id:language_and_engineering
 *
 */
public class OnBootReceiver extends BaseOnBootReceiver
{
    @Override
    protected void onDeviceBoot(Context context)
    {
        // アプリ側の設定を初期化
        FWUtil.receiveAppInfoAsFW( new AppSettings(context) );

    }

}
