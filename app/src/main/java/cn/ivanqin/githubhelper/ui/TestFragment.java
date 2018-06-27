package cn.ivanqin.githubhelper.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.ivanqin.githubhelper.MyGestureListener;
import cn.ivanqin.githubhelper.R;

public class TestFragment extends Fragment {
    private static final String TAG = TestFragment.class.getSimpleName();
    Button doubleClickBtn;
    private GestureDetector mGestureDetector;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle onSavedInstanceState) {
        Log.e(TAG, "init");
        View view = inflater.inflate(R.layout.test_fragment, container, false);
        doubleClickBtn = view.findViewById(R.id.test_fragment_double_click);
        mGestureDetector = new GestureDetector(getActivity(), new MyGestureListener());

        doubleClickBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
        return view;
    }

}
