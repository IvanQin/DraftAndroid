package cn.ivanqin.githubhelper.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.ivanqin.githubhelper.R;

public class MainFragment extends Fragment {
    private static final String TAG = MainFragment.class.getSimpleName();
    public static final String EXTRA_LIST_DATA_TEXT = "EXTRA_LIST_DATA_TEXT";
    private Button mShowToastBtn;
    private Button mStartContactBtn;
    private EditText mEtInput;
    private TextView mTvDisplay;
    private Button mBtnStartCount;
    private TextClock mTextClock;
    private TextView mTvCount;
    private Handler mHandler;
    private int count = 0;
    Runnable countRunnable = new Runnable() {
        @Override
        public void run() {
            mTvCount.setText(String.valueOf(count));
            count++;
            mHandler.postDelayed(this,1000);
        }
    };

    static final String BUNDLE_ET_TEXT = "BUNDLE_ET_TEXT";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle onSavedInstanceState) {
        Log.e(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        mTvDisplay = view.findViewById(R.id.tv_main_frag_display);

        mShowToastBtn = view.findViewById(R.id.show_toast);
        mEtInput = view.findViewById(R.id.et_main_frag_input);
        mBtnStartCount = view.findViewById(R.id.btn_main_frag_start_count);
        mBtnStartCount.setText(R.string.start);
        mTextClock = view.findViewById(R.id.tc_main_frag_clock);
        mTvCount = view.findViewById(R.id.tv_main_frag_count);

        mShowToastBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "click", Toast.LENGTH_LONG).show();

            }
        });

        mStartContactBtn = view.findViewById(R.id.btn_start_contact);
        mStartContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startContactActivity(getActivity());

            }
        });
        if (getArguments() != null) {
            mTvDisplay.setText(getArguments().getString(MenuActivity.ARGUMENT_MAIN_DISPLAY));
        }else{
            mTvDisplay.setText(R.string.def);
        }
        mHandler = new Handler();
        mBtnStartCount.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                for (int i = 1; i <= 10; i++) {
//                    final int fadedSecond = i;
//                    //每延迟一秒，发送一个Runnable对象
//                    mHandler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            mTvCount.setText(String.valueOf(10 - fadedSecond));
//                        }
//                    }, 1000 * i);
//                }
//            }
            @Override
            public void onClick(View view) {
                mHandler.postDelayed(countRunnable,1000);
                }

        });



        return view;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.e(TAG, "onSaveInstanceState: ");
        super.onSaveInstanceState(outState);
        //FIXME when to call on save instance state?
        outState.putString(BUNDLE_ET_TEXT, mEtInput.getText().toString());
    }


//    @Override
//    public void onDestroyView(){
//        super.onDestroyView();
//        outState.putString(BUNDLE_ET_TEXT, mEtInput.getText().toString());
//    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.e(TAG,"onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mEtInput.setText(savedInstanceState.getString(BUNDLE_ET_TEXT));
        }

//        mTvDisplay.setText(getActivity().getIntent().getStringExtra(MenuActivity.ARGUMENT_MAIN_DISPLAY));

    }

    @Override
    public void onStart() {

        super.onStart();
        Log.e(TAG,"onStart");
//        Log.e(TAG,getArguments().getString(MenuActivity.ARGUMENT_MAIN_DISPLAY));
        if (isAdded()) {

        }
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.e(TAG, "onResume: ");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.e(TAG, "onPause: ");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mHandler.removeCallbacks(countRunnable);
    }

    private void startContactActivity(Context context) {
        Intent starter = new Intent(context, ContactActivity.class);
        starter.putExtra(EXTRA_LIST_DATA_TEXT, "IvanQin");
        context.startActivity(starter);
    }




//    @Override
//    public void onCreate(Bundle onSavedInstanceState){
//        super.onCreate(onSavedInstanceState);
//        showToastBtn = findViewById
//
//
//    }
}
