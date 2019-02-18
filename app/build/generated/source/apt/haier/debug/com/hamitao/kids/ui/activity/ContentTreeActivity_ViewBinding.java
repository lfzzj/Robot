// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hamitao.framework.widgets.LoadingView;
import com.hamitao.kids.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ContentTreeActivity_ViewBinding implements Unbinder {
  private ContentTreeActivity target;

  @UiThread
  public ContentTreeActivity_ViewBinding(ContentTreeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ContentTreeActivity_ViewBinding(ContentTreeActivity target, View source) {
    this.target = target;

    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.rv_func_anim, "field 'mRecyclerView'", RecyclerView.class);
    target.viewLoading = Utils.findRequiredViewAsType(source, R.id.view_loading, "field 'viewLoading'", LoadingView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ContentTreeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecyclerView = null;
    target.viewLoading = null;
  }
}
