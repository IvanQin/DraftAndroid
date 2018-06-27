package cn.ivanqin.githubhelper.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import cn.ivanqin.githubhelper.R;

public class LoginActivity extends Activity {
    private EditText mEtLogin;
    private Button mBtnSubmit;
    final Context context = this;
    private final static String TAG = LoginActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){
        mEtLogin = findViewById(R.id.et_login_act);
        mBtnSubmit = findViewById(R.id.btn_login_submit);

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = mEtLogin.getText().toString();
                sendBack(text);
            }
        });

    }

    private void sendBack(String text){
        Log.e(TAG, "sendBack: enter");
        Intent mIntent = new Intent();
        mIntent.putExtra(MenuActivity.EXTRA_LOGIN_NAME,text);
        setResult(Activity.RESULT_OK,mIntent);
        finish();

    }
}
