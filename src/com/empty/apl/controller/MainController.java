package com.empty.apl.controller;


import com.empty.apl.activities.installation.InstallAppActivity;
import com.empty.apl.activities.installation.InstallCompletedActivity;
import com.empty.apl.activities.main.TopActivity;
import com.empty.framework.controller.BaseController;
import com.empty.framework.controller.routing.Router;


/**
 * メイン系画面のコントローラ。
 * @author id:language_and_engineering
 *
 */
public class MainController extends BaseController
{

    // 遷移元となるActivityごとに，submit()をオーバーロードする。


    /**
     * インストール画面からの遷移時
     */
    public static void submit(InstallAppActivity installAppActivity, boolean installExecutedFlag)
    {
        // インストールをスキップしたかどうか
        if( installExecutedFlag )
        {
            // インストール完了画面へ
            Router.go(installAppActivity, InstallCompletedActivity.class);
        }
        else
        {
            // トップ画面へ
            Router.go(installAppActivity, TopActivity.class);
        }
    }


    /**
     * インストール完了画面からの遷移時
     */
    public static void submit(InstallCompletedActivity activity) {
        // トップ画面へ
        Router.go(activity, TopActivity.class);
    }




}
