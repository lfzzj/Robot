// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hamitao.kids.R;
import com.hamitao.kids.view.ViewPagerCustomDuration;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FollowMeContentActivity_ViewBinding implements Unbinder {
  private FollowMeContentActivity target;

  private View view2131755216;

  @UiThread
  public FollowMeContentActivity_ViewBinding(FollowMeContentActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FollowMeContentActivity_ViewBinding(final FollowMeContentActivity target, View source) {
    this.target = target;

    View view;
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'viewPager'", ViewPagerCustomDuration.class);
    view = Utils.findRequiredView(source, R.id.btn_replay, "method 'onClick'");
    view2131755216 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    FollowMeContentActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.viewPager = null;

    view2131755216.setOnClickListener(null);
    view2131755216 = null;
  }
}
