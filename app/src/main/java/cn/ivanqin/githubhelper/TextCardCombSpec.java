package cn.ivanqin.githubhelper;

import android.telecom.Call;
import android.util.Log;

import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.StateValue;
import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.OnUpdateState;
import com.facebook.litho.annotations.Param;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.State;

@LayoutSpec
public class TextCardCombSpec {
    @OnCreateLayout
    public static Component onCreateLayout(
            ComponentContext c,
            @Prop float screenHeight,
//            @Prop(optional = true) CallBack callBack,
            @State boolean isCardGreen) {

        return Column.create(c)
//                .child(getTextComponent(c, callBack))
                .child(getTextComponent(c))
                .child(getCardComponent(c, isCardGreen))
                .heightDip(screenHeight - 100).build();
    }

    @OnUpdateState
    static void updateColor(StateValue<Boolean> isCardGreen, @Param boolean outside) {
        isCardGreen.set(outside);
    }

//    private static Component getTextComponent(final ComponentContext c, final CallBack callBack){
//        return MyEditText.create(c).listener(new MyEditTextSpec.OnTextUpdateListener() {
//            @Override
//            public void textChanged(String text) {
//                Log.i("TextChanged",text);
//                if (text.equals("IvanQin")){
//                    Log.i("Main","change color");
//                    TextCardComb.updateColorAsync(c,true);
//                }else{
//                    TextCardComb.updateColorAsync(c,false);
//                }
//                if (text.equals("open")){
//                    callBack.onOpenOther();
//                }
//            }
//        }).build();
//        return MyEditText.create(c).build();
//    }

    private static Component getTextComponent(final ComponentContext c) {
        return MyEditText.create(c)
                .myTextChangeEventH(TextCardComb.onMyTextChange(c))
                .build();
    }

    private static Component getCardComponent(ComponentContext c, boolean isGreen) {
        return MyCardView.create(c).isGreen(isGreen).build();
    }

    @OnEvent(MyTextChangeEvent.class)
    static void onMyTextChange(ComponentContext c, @FromEvent String text) {
        if (text.equals("IvanQin")) {
            Log.i("Main", "change color");
            TextCardComb.updateColorAsync(c, true);
        } else {
            TextCardComb.updateColorAsync(c, false);
        }

    }
//
//    public interface CallBack{
//        void onOpenOther();
//    }
}
