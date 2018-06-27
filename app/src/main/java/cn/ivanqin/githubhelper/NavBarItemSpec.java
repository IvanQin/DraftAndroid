package cn.ivanqin.githubhelper;

import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;

import com.facebook.litho.ClickEvent;
import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.Row;
import com.facebook.litho.StateValue;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.OnUpdateState;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;
import com.facebook.litho.annotations.State;
import com.facebook.litho.widget.Image;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaJustify;

@LayoutSpec
public class NavBarItemSpec {
    @OnCreateLayout
    static Component onCreateLayout(
            ComponentContext c,
            @Prop int imageId,
            @Prop int activeImageId,
            @Prop String title,
            @Prop boolean isActive

    ) {
        Resources resources = c.getResources();
        return Column.create(c)
//                .widthDip(100)
                .heightDip(70)
                .child(
                        Row.create(c).widthDip(100).heightDip(50).justifyContent(YogaJustify.CENTER).paddingDip(YogaEdge.ALL, 2)
                                .child(Image.create(c).drawableRes(isActive ? activeImageId : imageId))
                )
                .child(
                        Row.create(c).widthDip(100).heightDip(20).justifyContent(YogaJustify.CENTER).paddingDip(YogaEdge.HORIZONTAL, 10)
                                .child(Text.create(c).text(title).textColor(isActive ? resources.getColor(R.color.colorNavBarActive) : Color.BLACK).textSizeSp(10))

                )
//                .backgroundColor(Color.LTGRAY)
                .marginDip(YogaEdge.HORIZONTAL,10)
                .alignItems(YogaAlign.FLEX_START)
                .justifyContent(YogaJustify.CENTER)
                .build();
    }

}
