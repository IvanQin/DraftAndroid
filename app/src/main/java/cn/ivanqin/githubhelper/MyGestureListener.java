package cn.ivanqin.githubhelper;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MyGestureListener implements GestureDetector.OnGestureListener {
    private final static String TAG = MyGestureListener.class.getSimpleName();
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.e(TAG,"onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        Log.e(TAG,"on show press");

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        Log.e(TAG,"on single tap up");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}
