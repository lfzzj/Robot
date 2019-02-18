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

public class AlbumViewerActivity_ViewBinding implements Unbinder {
  private AlbumViewerActivity target;

  @UiThread
  public AlbumViewerActivity_ViewBinding(AlbumViewerActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AlbumViewerActivity_ViewBinding(AlbumViewerActivity target, View source) {
    this.target = target;

    target.viewPager = Utils.findRequiredViewAsType(source, R.id.view_pager_pic, "field 'viewPager'", ViewPagerCustomDuration.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AlbumViewerActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.viewPager = null;
  }
}
