package com.vzan.geetionlib.widget.refreshlayout;

import android.widget.Scroller;

/**
 * @author liu-feng 
 * @date 2017/3/14 0014.
 * Email:w710989327@foxmail.com
 */
public class AutoScroller implements Runnable {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Scroller mScroller;
    private ScrollerCallback mScrollerCallback;
    private int mLastY;

    private boolean isRunning, mAbort; // 是否运行、终止

    public AutoScroller(SwipeRefreshLayout refreshLayout) {
        mSwipeRefreshLayout = refreshLayout;
        mScroller = new Scroller(refreshLayout.getContext());
    }

    public void setScrollerCallback(ScrollerCallback scrollerCallback) {
        this.mScrollerCallback = scrollerCallback;
    }

    @Override
    public void run() {
        boolean finish = !mScroller.computeScrollOffset();
        int currY = mScroller.getCurrY();
        int diffY = currY - mLastY;
        if (finish) {
            mLastY = 0;
            isRunning = false;
            mSwipeRefreshLayout.removeCallbacks(this);
            if (!mAbort) {
                if (mScrollerCallback != null)
                    mScrollerCallback.scrollFinished();
            }
        } else {
            mLastY = currY;
            if (mScrollerCallback != null)
                mScrollerCallback.scrollBy(diffY);
            mSwipeRefreshLayout.post(this);
        }

    }

    /**
     * 终止并回弹 if running
     */
    public void abortIfRunning() {
        if (isRunning) {
            if (!mScroller.isFinished()) {
                mAbort = true;
                mScroller.forceFinished(true);
            }
            mLastY = 0;
            isRunning = false;
            mSwipeRefreshLayout.removeCallbacks(this);
            if (!mAbort) {
                if (mScrollerCallback != null)
                    mScrollerCallback.scrollFinished();
            }
            mAbort = false;
        }
    }

    /**
     * 自动刷新
     * @param scrollY 滑动的距离
     * @param duration 需要的时间
     */
    public void autoScroll(int scrollY, int duration) {
        mSwipeRefreshLayout.removeCallbacks(this);
        mLastY = 0;
        if (!mScroller.isFinished()) {
            mScroller.forceFinished(true);
        }
        mScroller.startScroll(0, 0, 0, scrollY, duration);
        mSwipeRefreshLayout.post(this);
        isRunning = true;
    }

    public interface ScrollerCallback {
        /**
         * 滑动过程
         * @param y 每次计算滑动的距离
         */
        void scrollBy(final float y);

        /**
         * 滑动结束
         */
        void scrollFinished();
    }
}
