package com.empty.apl.activities.main;

import java.util.Random;

import android.content.Context;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.empty.apl.R;
import com.empty.apl.common.AppSettings;
import com.empty.apl.common.ShakeListener;
import com.empty.apl.common.ShakeListener.OnShakeListener;
import com.empty.framework.activities.base.BaseNormalActivity;
import com.empty.framework.ui.menu.OptionMenuBuilder;
import com.empty.framework.ui.view.MTextView;

/**
 * サンプルのトップ画面。
 * 
 * @author id:language_and_engineering
 * 
 */
public class TopActivity extends BaseNormalActivity implements GestureDetector.OnGestureListener
{
    private ShakeListener mShakeListener;
    private GestureDetector mGestureDetector;
    private SensorManager mSensorManager;
    private OnShakeListener mOnShakeListener;
    private boolean working;

    private SoundPool soundPool;
    private int soundId;
    private int gunSoundId;
    private int emptySoundId;
    private int reloadSoundId;

    private int counter;
    private static int MAX_COUNT = 6;

    @Override
    public void defineContentView() {
        final TopActivity activity = this;

        Log.d("Gun", "Log test");
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        gunSoundId = soundPool.load(this, R.raw.gun001, 1);
        emptySoundId = soundPool.load(this, R.raw.empty, 1);
        reloadSoundId = soundPool.load(this, R.raw.reload2, 1);

        counter = MAX_COUNT;
        working = false;

        mSensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
        mShakeListener = new ShakeListener();
        mOnShakeListener = new OnShakeListener() {

            @Override
            public void onShaked(int direction) {
                // TODO 自動生成されたメソッド・スタブ
                if ((direction & ShakeListener.DIRECTION_X) > 0
                        || (direction & ShakeListener.DIRECTION_Y) > 0
                        || (direction & ShakeListener.DIRECTION_Z) > 0) {
                    Log.d("GUN", "SENSOR ACTIVATED");
                    reload();
                }
            }

        };

        mGestureDetector = new GestureDetector(this, this);
        mShakeListener.registerListener(mSensorManager, mOnShakeListener, true);
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

    // タッチイベント時に呼ばれる
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("Gun", "onTouch");
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d("Gun", "Flick detected");

        if (!working) {
            working = true;

            soundId = (counter > 0) ? gunSoundId : emptySoundId;
            counter--;

            // 音は非同期で再生する
            new MusicPlayTask().execute(null);

            working = false;
        }
        return false;
    }

    public class MusicPlayTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            sound();
            return null;
        }
    }

    private void sound() {
        // 音楽読み込み
        soundPool.play(soundId, 1, 1, 1, 0, 1);
    }

    private void reload() {
        if (!working) {
            working = true;

            counter = MAX_COUNT;
            soundId = reloadSoundId;

//            // 音は非同期で再生する
//            new MusicPlayTask().execute(null);

            soundPool.play(soundId, 1, 1, 1, 0, 1);

            working = false;
        }
    }

    @Override
    public void onDestroy() {
        mShakeListener.unregisterListener(mSensorManager);
    }
}
