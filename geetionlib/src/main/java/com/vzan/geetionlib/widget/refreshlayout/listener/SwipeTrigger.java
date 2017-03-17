package com.vzan.geetionlib.widget.refreshlayout.listener;

/**
 * @author liu-feng 
 * @date 2017/3/14 0014.
 * Email:w710989327@foxmail.com
 */
public interface SwipeTrigger {
    void onPrepare();

    void onMove(int diffY, boolean isComplete, boolean automatic);

    void onRelease();

    void onComplete();

    void onReset();
}
