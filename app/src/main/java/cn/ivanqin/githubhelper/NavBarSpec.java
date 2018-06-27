package cn.ivanqin.githubhelper;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.facebook.litho.ClickEvent;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.Row;
import com.facebook.litho.StateValue;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateInitialState;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.OnUpdateState;
import com.facebook.litho.annotations.Param;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.State;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaPositionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@LayoutSpec
public class NavBarSpec {
    @OnCreateLayout
    static Component onCreateLayout(ComponentContext c, @State List<Boolean> isActive ,@Prop float screenHeight, @Prop float screenWidth) {
        Resources resources = c.getResources();
        return Row.create(c)
                .widthDip(screenWidth)
                .justifyContent(YogaJustify.CENTER)
                .child(NavBarItem.create(c)
                        .activeImageId(R.drawable.ic_home_active_24dp)
                        .imageId(R.drawable.ic_home_black_24dp)
                        .title(resources.getString(R.string.home_icon))
                        .clickHandler(NavBar.onFaceClicked(c, 0))
                        .isActive(isActive.get(0)))
                .child(NavBarItem.create(c)
                        .activeImageId(R.drawable.ic_search_active_24dp)
                        .imageId(R.drawable.ic_search_black_24dp)
                        .title(resources.getString(R.string.search_icon))
                        .clickHandler(NavBar.onFaceClicked(c, 1))
                        .isActive(isActive.get(1)))
                .child(NavBarItem.create(c)
                        .activeImageId(R.drawable.ic_account_circle_active_24dp)
                        .imageId(R.drawable.ic_account_circle_black_24dp)
                        .title(resources.getString(R.string.account_icon))
                        .clickHandler(NavBar.onFaceClicked(c, 2))
                        .isActive(isActive.get(2))
                )
                .backgroundColor(Color.LTGRAY)
//                .positionDip(YogaEdge.BOTTOM, 0)
//                .positionType(YogaPositionType.ABSOLUTE)
                .build();
    }

    @OnEvent(ClickEvent.class)
    static void onFaceClicked(
            ComponentContext c,
            @Param int key) {
        NavBar.onItemUpdateAsync(c, key);
        switch (key){
            case 1:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                c.startActivity(sendIntent);
                break;
            case 2:
                Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
                // Or map point based on latitude/longitude
                // Uri location = Uri.parse("geo:37.422219,-122.08364?z=14"); // z param is zoom level
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                c.startActivity(mapIntent);
                break;
            case 0:
                Intent intent = new Intent(c,TestActivity.class);
                intent.putExtra(MainActivity.SEND_TEXT,"Hello Ivan");
                c.startActivity(intent);
                break;
        }

    }

    @OnUpdateState
    static void onItemUpdate(StateValue<List<Boolean>> isActive, @Param int key) {
        List<Boolean> l = Arrays.asList(false,false,false);
        l.set(key,true);
        isActive.set(l);
    }

    @OnCreateInitialState
    static void createInitialState(
            ComponentContext c,
            StateValue<List<Boolean>> isActive) {
        List<Boolean> l = Arrays.asList(false,false,false);
        isActive.set(l);

    }


}
