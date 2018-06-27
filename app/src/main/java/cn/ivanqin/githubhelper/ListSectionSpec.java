package cn.ivanqin.githubhelper;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;

import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.sections.Children;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.annotations.GroupSectionSpec;
import com.facebook.litho.sections.annotations.OnCreateChildren;
import com.facebook.litho.sections.common.DataDiffSection;
import com.facebook.litho.sections.common.RenderEvent;
import com.facebook.litho.sections.common.SingleComponentSection;
import com.facebook.litho.sections.widget.ListRecyclerConfiguration;
import com.facebook.litho.sections.widget.RecyclerCollectionComponent;
import com.facebook.litho.widget.ComponentRenderInfo;
import com.facebook.litho.widget.RenderInfo;

import java.util.ArrayList;
import java.util.List;

@GroupSectionSpec
public class ListSectionSpec {
    @OnCreateChildren
    static Children onCreateChildren(final SectionContext c) {
        Children.Builder builder = Children.create();
//
        DataDiffSection section = DataDiffSection.<Integer>create(c).data(generateData(32))
                .renderEventHandler(ListSection.onRender(c)).build();
        SingleComponentSection singleComponentSection = SingleComponentSection
                .create(c)
                .component(RecyclerCollectionComponent.create(c).section(section)
                        .recyclerConfiguration(new ListRecyclerConfiguration(LinearLayoutManager.HORIZONTAL,false,ListRecyclerConfiguration.SNAP_TO_CENTER))
                        .disablePTR(true)
                        .canMeasureRecycler(true) // must be set
                        .build()
                )
                .build();
        return builder
                .child(singleComponentSection)
                .child(section)
                .build();

    }

//    private static List<Integer> addNumbers(int count) {
//        List<Integer> res = new ArrayList<>();
//        for (int i = 0; i < count; i++)
//            res.add(i);
//        return res;
//    }
    private static List<Integer> generateData(int count) {
        final List<Integer> data = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            data.add(i);
        }
        return data;
    }

    @OnEvent(RenderEvent.class)
    static RenderInfo onRender(final SectionContext c, @FromEvent Integer model){
        return ComponentRenderInfo.create()
                .component(ListItem.create(c)
                        .color(model % 2 == 0 ? Color.WHITE : Color.LTGRAY)
                        .title(String.valueOf(model) + c.getResources().getString(R.string.author_name))
                        .subtitle("Litho tutorial")
                        .build())
                .build();

    }

}
