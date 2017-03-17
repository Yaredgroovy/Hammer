package com.vzan.geetionlib.widget.refreshlayout.style.header;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.vzan.geetionlib.R;
import com.vzan.geetionlib.widget.refreshlayout.listener.SwipeRefreshTrigger;
import com.vzan.geetionlib.widget.refreshlayout.listener.SwipeTrigger;
import com.vzan.geetionlib.widget.refreshlayout.style.google.RingProgressDrawable;

public class GoogleRefreshHeaderView extends FrameLayout implements SwipeTrigger, SwipeRefreshTrigger {
    private ImageView ivRefresh;

    private int mTriggerOffset;

    private RingProgressDrawable ringProgressDrawable;

    public GoogleRefreshHeaderView(Context context) {
        this(context, null);
    }

    public GoogleRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GoogleRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ringProgressDrawable = new RingProgressDrawable(context);
        ringProgressDrawable.setColors(
                R.color.swiperefresh_color1,
                R.color.swiperefresh_color2,
                R.color.swiperefresh_color3,
                R.color.swiperefresh_color4
        );
        mTriggerOffset = context.getResources().getDimensionPixelOffset(R.dimen.refresh_trigger_offset_google);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ivRefresh = (ImageView) findViewById(R.id.ivRefresh);
        ivRefresh.setBackgroundDrawable(ringProgressDrawable);
    }

    @Override
    public void onRefresh() {
        ringProgressDrawable.start();
    }

    @Override
    public void onPrepare() {

    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            ringProgressDrawable.setPercent(y / (float) mTriggerOffset);
        }
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        ringProgressDrawable.stop();
    }

    @Override
    public void onReset() {

    }
}
