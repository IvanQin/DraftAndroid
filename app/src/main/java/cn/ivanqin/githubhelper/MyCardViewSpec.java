package cn.ivanqin.githubhelper;

import android.graphics.Color;
import android.util.Log;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.StateValue;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateInitialState;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnUpdateState;
import com.facebook.litho.annotations.Param;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.State;
import com.facebook.litho.widget.Card;
import com.facebook.litho.widget.Text;

import java.util.Arrays;
import java.util.List;

@LayoutSpec
public class MyCardViewSpec {


    @OnCreateLayout
    public static Component onCreateMyCardView(ComponentContext c, @Prop boolean isGreen) {
        return Card.create(c).content(Text.create(c).text("test").build())
                .heightDip(50).widthDip(50).elevationDip(10)
                .cardBackgroundColor(isGreen ? Color.GREEN : Color.GRAY)
                .build();
    }

//    @OnUpdateState
//    static void onItemUpdate(StateValue<Boolean> isGreen, @Param boolean outside) {
//        Log.d("onItemUpdate", isGreen.toString());
//        isGreen.set(outside);
//    }
//
//    public interface UpdateColorListener{
//        void colorChanged();
//    }
}

