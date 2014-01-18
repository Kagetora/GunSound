package com.empty.apl.activities.main;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.empty.apl.R;
import com.empty.apl.common.ShakeListener;
import com.empty.apl.common.ShakeListener.OnShakeListener;
import com.empty.framework.activities.base.BaseNormalActivity;
import com.empty.framework.ui.UIBuilder;
import com.empty.framework.ui.menu.OptionMenuBuilder;
import com.empty.framework.ui.view.MTextView;

/**
 * サンプルのトップ画面。
 * 
 * @author id:language_and_engineering
 * 
 */
public class TopActivity extends BaseNormalActivity
{
    private ShakeListener mShakeListener;

    MediaPlayer bgm;
    MTextView b1;

    @Override
    public void defineContentView() {
        final TopActivity activity = this;

        bgm = MediaPlayer.create(activity, R.raw.gun);
        // ここに，画面上のUI部品の定義を記述する。
        SensorManager mSensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
        mShakeListener = new ShakeListener();

        mShakeListener.registerListener(mSensorManager, mOnShakeListener, true);

        new UIBuilder(context)
                .add(
                        b1 = new MTextView(activity)
                                .text("↓\n↓\n↓\n↓\n↓\n↓")
                                .widthFillParent()
                                .heightWrapContent()
                                .touch(new FlickTouchListener(activity, b1))

                )
                .display();
    }

    @Override
    public OptionMenuBuilder defineMenu()
    {
        final TopActivity activity = this;

        // オプションメニューを構築
        return new OptionMenuBuilder(context);
    }

    @Override
    public void onBackPressed()
    {
        // 戻るキーが押されたら終了
        moveTaskToBack(true);
    }

    private OnShakeListener mOnShakeListener = new OnShakeListener() {
        // @Override
        public void onShaked(int direction) {
            if ((direction & ShakeListener.DIRECTION_X) > 0
                    || (direction & ShakeListener.DIRECTION_Y) > 0
                    || (direction & ShakeListener.DIRECTION_Z) > 0) {
                bgm.start();
            }
        }
    };

    private void sound() {
        // 音楽読み込み
        bgm.start();
    }

    // --------------------------------------------------------------------------
    // フリックされた方向を算出する
    //
    // --------------------------------------------------------------------------
    private class FlickTouchListener implements View.OnTouchListener
    {

        // 最後にタッチされた座標
        private float startTouchX;
        private float startTouchY;

        // 現在タッチ中の座標
        private float nowTouchedX;
        private float nowTouchedY;

        private final Activity activity;
        private final View view;
        private MediaPlayer bgm;

        public FlickTouchListener(Activity activity, View view) {
            this.activity = activity;
            this.view = view;
            MediaPlayer bgm = MediaPlayer.create(activity, R.raw.gun);

        }

        @Override
        public boolean onTouch(View v_, MotionEvent event_)
        {
            // タッチされている指の本数
            Log.v("motionEvent", "--touch_count = " + event_.getPointerCount());

            MediaPlayer bgm = MediaPlayer.create(activity, R.raw.gun);
            // タッチされている座標
            Log.v("Y", "" + event_.getY());
            Log.v("X", "" + event_.getX());

            switch (event_.getAction()) {

            // タッチ
            case MotionEvent.ACTION_DOWN:
                Log.v("motionEvent", "--ACTION_DOWN");
                startTouchX = event_.getX();
                startTouchY = event_.getY();
                break;

            // タッチ中に追加でタッチした場合
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.v("motionEvent", "--ACTION_POINTER_DOWN");
                break;

            // スライド
            case MotionEvent.ACTION_MOVE:
                Log.v("motionEvent", "--ACTION_MOVE");
                break;

            // タッチが離れた
            case MotionEvent.ACTION_UP:
                Log.v("motionEvent", "--ACTION_UP");
                nowTouchedX = event_.getX();
                nowTouchedY = event_.getY();

                if (startTouchY > nowTouchedY) {
                    if (startTouchX > nowTouchedX) {
                        if ((startTouchY - nowTouchedY) > (startTouchX - nowTouchedX)) {
                            if (startTouchY > nowTouchedY) {
                                Log.v("Flick", "--上");
                                // 上フリック時の処理を記述する
                                break;

                            }
                        }
                        else if ((startTouchY - nowTouchedY) < (startTouchX - nowTouchedX)) {
                            if (startTouchX > nowTouchedX) {
                                Log.v("Flick", "--左");
                                // 左フリック時の処理を記述する
                                break;
                            }
                        }
                    }
                    else if (startTouchX < nowTouchedX) {
                        if ((startTouchY - nowTouchedY) > (nowTouchedX - startTouchX)) {
                            if (startTouchY > nowTouchedY) {
                                Log.v("Flick", "--上");
                                // 上フリック時の処理を記述する
                                break;
                            }
                        }
                        else if ((startTouchY - nowTouchedY) < (nowTouchedX - startTouchX)) {
                            if (startTouchX < nowTouchedX) {
                                Log.v("Flick", "--右");
                                // 右フリック時の処理を記述する
                                break;
                            }
                        }
                    }
                }
                else if (startTouchY < nowTouchedY) {
                    if (startTouchX > nowTouchedX) {
                        if ((nowTouchedY - startTouchY) > (startTouchX - nowTouchedX)) {
                            if (startTouchY < nowTouchedY) {
                                Log.v("Flick", "--下");
                                // 下フリック時の処理を記述する
                                bgm.start();
                                break;
                            }
                        }
                        else if ((nowTouchedY - startTouchY) < (startTouchX - nowTouchedX)) {
                            if (startTouchX > nowTouchedX) {
                                Log.v("Flick", "--左");
                                // 左フリック時の処理を記述する
                                break;
                            }
                        }
                    }
                    else if (startTouchX < nowTouchedX) {
                        if ((nowTouchedY - startTouchY) > (nowTouchedX - startTouchX)) {
                            if (startTouchY < nowTouchedY) {
                                Log.v("Flick", "--下");
                                // 下フリック時の処理を記述する
                                bgm.start();
                                break;
                            }
                        }
                        else if ((nowTouchedY - startTouchY) < (nowTouchedX - startTouchX)) {
                            if (startTouchX < nowTouchedX) {
                                Log.v("Flick", "--右");
                                // 右フリック時の処理を記述する
                                break;
                            }
                        }
                    }
                }

                Log.v("Flick", "--フリックなし");
                bgm.start();
                break;

            // アップ後にほかの指がタッチ中の場合
            case MotionEvent.ACTION_POINTER_UP:
                Log.v("motionEvent", "--ACTION_POINTER_UP");
                bgm.start();
                break;

            // UP+DOWNの同時発生(タッチのキャンセル）
            case MotionEvent.ACTION_CANCEL:
                Log.v("motionEvent", "--ACTION_CANCEL");
                bgm.start();
                break;

            // ターゲットとするUIの範囲外を押下
            case MotionEvent.ACTION_OUTSIDE:
                Log.v("motionEvent", "--ACTION_OUTSIDE");
                bgm.start();
                break;
            }
            return (true);
        }

        private void sound() {
            // 音楽読み込み
            bgm.start();
        }
    }
}
