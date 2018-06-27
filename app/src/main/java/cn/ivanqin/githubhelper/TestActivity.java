package cn.ivanqin.githubhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.facebook.litho.Row;
import com.facebook.litho.widget.Text;

public class TestActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String text = intent.getStringExtra(MainActivity.SEND_TEXT);
        ComponentContext c = new ComponentContext(this);
        Component component = Text.create(c
        ).text(text).build();
        Component row = Row.create(c).child(component).build();
        final LithoView lithoView = LithoView.create(
                this /* context */,
                component
        );
        setContentView(lithoView);

    }
}
