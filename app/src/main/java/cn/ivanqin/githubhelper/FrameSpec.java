package cn.ivanqin.githubhelper;

import android.graphics.Color;
import android.util.Log;
import android.widget.Button;

import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.FocusChangedEvent;
import com.facebook.litho.Row;
import com.facebook.litho.annotations.Event;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.widget.RecyclerCollectionComponent;
import com.facebook.litho.widget.EditText;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaJustify;

@LayoutSpec
public class FrameSpec {
    @PropDefault
    final static float virtualBarHeight = 80;

    @OnCreateLayout
    static Component onCreateFrame(
            ComponentContext c,
            @Prop float screenWidth,
            @Prop float screenHeight,
            @Prop(optional = true) float virtualBarHeight
    ) {
        Column.Builder builder = Column.create(c);
        RecyclerCollectionComponent.Builder builder1 = RecyclerCollectionComponent.create(c);
        final Component component = RecyclerCollectionComponent.create(c).disablePTR(true)
                .section(ListSection.create(new SectionContext(c)).build()).build();
        builder1 = builder1.section(ListSection.create(new SectionContext(c)).build());
//        for (int i = 0; i < 20; i++){
//        }
//
        return Column.create(c)
                .child(NavBar.create(c).screenHeight(screenHeight).screenWidth(screenWidth).build())

//                .child(Row.create(c).widthDip(screenWidth).heightDip(virtualBarHeight))
//                .child(Row.create(c).widthDip(screenWidth)
//                        .child(NavBar
//                                .create(c)
//                                .screenHeight(screenHeight)
//                                .screenWidth(screenWidth).build()))
//                .child(component)
//                .child(Row.create(c).justifyContent(YogaJustify.CENTER).child(
//                        EditText.create(c).hint("123").textSizeSp(20)
////                                .editorActionListener()
//                                .focusChangeHandler(Frame.onFocusChanged(c))
//                                .isSingleLine(true).build()
//                ))
//                .child(Text.create(c).text("bbb").textSizeSp(20))
//                .child(Row.create(c).build())
                .alignItems(YogaAlign.CENTER) // cross axis
//                .backgroundColor(Color.GRAY)
                .justifyContent(YogaJustify.FLEX_START) // main axis
                .reverse(true)
                .build();
    }

    @OnEvent(FocusChangedEvent.class)
    static void onFocusChanged(ComponentContext c) {
        Log.d("IvanQin","focus changed");
    }
}

