package cn.ivanqin.githubhelper;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.common.SingleComponentSection;
import com.facebook.litho.sections.widget.RecyclerCollectionComponent;
import com.facebook.litho.widget.Card;
import com.facebook.litho.widget.LinearLayoutInfo;
import com.facebook.litho.widget.Progress;
import com.facebook.litho.widget.Recycler;
import com.facebook.litho.widget.RecyclerBinder;
import com.facebook.litho.widget.Text;
import com.facebook.soloader.SoLoader;

public class MainActivity extends Activity {
    public final static String SEND_TEXT = "cn.ivanqin.githubhelper.MainActivity";
    private static LithoView root;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        int height = dm.heightPixels;       // 屏幕高度（像素）
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）
        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
        int screenHeight = (int) (height / density);// 屏幕高度(dp)

        Log.d(this.getClass().getName(), String.valueOf(screenHeight));

        final ComponentContext c = new ComponentContext(this);
        final Component component = Frame.create(c)
                .screenHeight(screenHeight)
                .screenWidth(screenWidth)
                .build();


//        TextCardCombSpec.CallBack callBack = new TextCardCombSpec.CallBack() {
//            @Override
//            public void onOpenOther() {
//                root.setComponent(component);
//            }
//        };

        final Component test = TextCardComb
                .create(c)
//                .callBack(callBack)
                .screenHeight(screenHeight)
                .build();

        root = LithoView.create(
                this /* context */,
                test
        );
        setContentView(root);

    }

//    @OnEvent(JumpToOtherEvent.class)
//    static void onJumpToOtherEvent(ComponentContext c, @FromEvent String text){
//        if (text.equals("open")){
//            root.setComponent();
//        }
//
//    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check the if the required resource is enabled.
        // http://hukai.me/android-training-course-in-chinese/basics/activity-lifecycle/stopping.html
        Log.d("IvanQin", "onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("IvanQin", "onResume");

    }

    @Override
    public void onPause() {
        // DO NOT write user info to DB.
        super.onPause();
        Log.d("IvanQin", "onPause");
    }

    @Override
    public void onStop() {
        // Write user info to DB. Do some CPU-intensive work.
        // http://hukai.me/android-training-course-in-chinese/basics/activity-lifecycle/stopping.html
        super.onStop();
        Log.d("IvanQin", "onStop");

//        ContentValues values = new ContentValues();
//        values.put(NotePad.Notes.COLUMN_NAME_NOTE, getCurrentNoteText());
//        values.put(NotePad.Notes.COLUMN_NAME_TITLE, getCurrentNoteTitle());
//
//        getContentResolver().update(
//                mUri,    // The URI for the note to update.
//                values,  // The map of column names and new values to apply to them.
//                null,    // No SELECT criteria are used.
//                null     // No WHERE columns are used.
//        );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        android.os.Debug.stopMethodTracing();
    }


}
