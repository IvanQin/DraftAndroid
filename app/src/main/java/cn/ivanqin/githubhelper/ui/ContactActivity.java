package cn.ivanqin.githubhelper.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.ivanqin.githubhelper.R;

public class ContactActivity extends Activity {
    private static final String TAG = ContactActivity.class.getSimpleName();
    public static final int REQ_YES_NO_CALL = 0;
    private ListView mContactLv;
    private ArrayList<Map<String, String>> mContactList;
//    private String[] data = {"Apple", "Banana", "Orange", "Watermelon",
//            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Bundle bundle = getIntent().getExtras();
        String contactListText = "default";
        if (bundle != null) {
            contactListText = bundle.getString(MainFragment.EXTRA_LIST_DATA_TEXT);
        }
        mContactList = genData(contactListText);
        init();
    }

    @Override
    public void onStart() {
        super.onStart();
//        mContactLv.smoothScrollToPosition(0);
    }

    @Override
    public void onResume() {
        super.onResume();
//        mContactLv.smoothScrollToPosition(0);
//        showDialog();
        genDialogFragment();
    }

    private void init() {
        mContactLv = findViewById(R.id.lv_contact);
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                mContactList,
                R.layout.contact_item,
                new String[]{"ItemTitle", "ItemText"},
                new int[]{R.id.item_title, R.id.item_text}

        );

        mContactLv.setAdapter(adapter);
    }

    private ArrayList<Map<String, String>> genData(String itemText) {
        ArrayList<Map<String, String>> mylist = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("ItemTitle", "This is Title.....");
            map.put("ItemText", itemText);
            mylist.add(map);
        }
        return mylist;
    }

    private void showDialog() {
        // which is deprecated above API level 13
        // (https://stackoverflow.com/questions/7977392/android-dialogfragment-vs-dialog/21032871#21032871)
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.select);
        builder.setMessage(R.string.scrollToTheTop);
//        builder.setItems()
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mContactLv.smoothScrollToPosition(0);
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mContactLv.smoothScrollToPosition(mContactList.size() - 1);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void genDialogFragment() {
//        DialogFragment dialog = new YesNoDialog();
        String title = getResources().getString(R.string.select);
        String message = getResources().getString(R.string.scrollToTheTop);
        final Context context = this;
        DialogFragment dialog = YesNoDialog.newInstance(title, message, new YesNoDialog.Callback() {
            @Override
            public void positiveCallBack() {
                Toast.makeText(context, R.string.yes, Toast.LENGTH_LONG).show();
            }

            @Override
            public void negativeCallBack() {
                Toast.makeText(context, R.string.no, Toast.LENGTH_LONG).show();

            }
        });
//        dialog.setTargetFragment(,REQ_YES_NO_CALL);
        dialog.show(getFragmentManager(), YesNoDialog.TAG);
    }

//    public void doPostivieClick(){
//        Toast.makeText(this,R.string.yes,Toast.LENGTH_LONG).show();
//    }
//
//    public void doNegativeClick(){
//        Toast.makeText(this,R.string.no,Toast.LENGTH_LONG).show();
//
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (resultCode){
//            case REQ_YES_NO_CALL:
//                if (requestCode == Activity.RESULT_OK){
//                    Toast.makeText(this,R.string.yes,Toast.LENGTH_LONG).show();
//
//                }else if (requestCode == Activity.RESULT_CANCELED){
//                    Toast.makeText(this,R.string.no,Toast.LENGTH_LONG).show();
//                }
//        }
//
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "Action down");
                return true;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "Action up");
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG,"Action move");
                return true;
            case MotionEvent.ACTION_OUTSIDE:
                Log.e(TAG,"Action outside");
                return true;
            default:
                return super.onTouchEvent(event);
        }


    }

}
