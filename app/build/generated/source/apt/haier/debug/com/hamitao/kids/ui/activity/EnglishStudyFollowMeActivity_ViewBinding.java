// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hamitao.kids.R;
import com.hamitao.kids.view.ViewPagerCustomDuration;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EnglishStudyFollowMeActivity_ViewBinding implements Unbinder {
  private EnglishStudyFollowMeActivity target;

  @UiThread
  public EnglishStudyFollowMeActivity_ViewBinding(EnglishStudyFollowMeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EnglishStudyFollowMeActivity_ViewBinding(EnglishStudyFollowMeActivity target,
      View source) {
    this.target = target;

    target.viewPager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'viewPager'", ViewPagerCustomDuration.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EnglishStudyFollowMeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.viewPager = null;
  }
}
