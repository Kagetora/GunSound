package com.empty.framework.ui.view;

import java.util.HashMap;




import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * TextViewのラッパークラス。
 * @author id:language_and_engineering
 *
 */
public class MSeekBar extends SeekBar implements IFWView
{

    public MSeekBar(Context context) {
        super(context);
    }


    public MSeekBar touch (OnTouchListener l){
        setOnTouchListener(l);
        return this;
    }
    // パラメータ保持
    HashMap<String, Object> view_params = new HashMap<String, Object>();

    @Override
    public Object getViewParam(String key) {
        return view_params.get(key);
    }

    @Override
    public void setViewParam(String key, Object val) {
        view_params.put(key, val);
    }


    // 以下は属性操作


    public MSeekBar paddingPx( int px ) {
        setPadding(px, px, px, px);
        return this;
    }

    public MSeekBar widthWrapContent() {
        setViewParam("layout_width", ViewGroup.LayoutParams.WRAP_CONTENT );
        return this;
    }

    public MSeekBar widthFillParent() {
        setViewParam("layout_width", ViewGroup.LayoutParams.FILL_PARENT );
        return this;
    }

    public MSeekBar heightWrapContent() {
        setViewParam("layout_height", ViewGroup.LayoutParams.WRAP_CONTENT );
        return this;
    }

    public MSeekBar visible() {
        setVisibility(VISIBLE);
        return this;
    }

    public MSeekBar invisible() {
        setVisibility(GONE);
        return this;
    }

    public MSeekBar progress(int progress) {
        setProgress(progress);
        return this;
    }
    
    public MSeekBar max(int max){
        setMax(max);
        return this;
    }


    public MSeekBar change(OnSeekBarChangeListener l) {
        setOnSeekBarChangeListener(l);
        return this;
    }
    
    public MSeekBar thumOffset(int t){
        setThumbOffset(t);
        return this;
    }
}
