package cn.ivanqin.githubhelper;

import android.graphics.Color;
import android.util.Log;

import com.facebook.litho.ClickEvent;
import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.StateValue;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateInitialState;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.OnUpdateState;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.State;
import com.facebook.litho.widget.Text;

import static com.facebook.yoga.YogaEdge.ALL;

@LayoutSpec
public class ListItemSpec {

    @OnCreateLayout
    static Component onCreateLayOut(
            ComponentContext c, @Prop int color,
            @Prop String title,
            @Prop String subtitle,
            @State boolean isClicked
    ) {
        return Column.create(c).paddingDip(ALL, 16).backgroundColor(color)
                .child(Text.create(c).text(title).textSizeSp(40).textColor(isClicked ? Color.RED : Color.BLACK))
                .child(Text.create(c).text(subtitle).textSizeSp(20))
                .clickHandler(ListItem.onClick(c))
                .build();
    }

    @OnEvent(ClickEvent.class)
    static void onClick(ComponentContext c, @State boolean isClicked) {
        Log.i("User", "clicked");
        ListItem.updateTextStateAsync(c);
    }

//    @OnCreateInitialState
//    static void createInitialState(ComponentContext c,
//                                   StateValue<Boolean> isClicked,
//                                   @Prop boolean initClicked
//                                   ){
//        isClicked.set(initClicked);
//    }

    @OnUpdateState
    static void updateTextState(StateValue<Boolean> isClicked) {
        isClicked.set(!isClicked.get());
    }


}
