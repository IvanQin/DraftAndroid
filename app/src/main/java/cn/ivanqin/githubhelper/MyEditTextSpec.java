package cn.ivanqin.githubhelper;

import android.util.Log;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.EventHandler;
import com.facebook.litho.annotations.Event;
import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.EditText;
import com.facebook.litho.widget.TextChangedEvent;

@LayoutSpec(events = {MyTextChangeEvent.class, JumpToOtherEvent.class})
public class MyEditTextSpec {


    @OnCreateLayout
    static public Component onCreateEditText(ComponentContext c
                                             ) {
        return EditText.create(c).textChangedEventHandler(MyEditText.onTextChanged(c))
                .textSizeDip(16)
                .build();
    }

    @OnEvent(TextChangedEvent.class)
    static public void onTextChanged(ComponentContext c,
//                                     @Prop EventHandler jumpToOther,
                                     @Prop EventHandler myTextChangeEventH,
                                     @FromEvent String text) {
        Log.d("MyEditTextSpec", text);
        MyEditText.dispatchMyTextChangeEvent(myTextChangeEventH, text);
//        MyEditText.dispatchJumpToOtherEvent(MyEditText.getJumpToOtherEventHandler(c),text);

//        listener.textChanged(text);
    }

//    public interface OnTextUpdateListener{
//        void textChanged(String text);
//    }
}
