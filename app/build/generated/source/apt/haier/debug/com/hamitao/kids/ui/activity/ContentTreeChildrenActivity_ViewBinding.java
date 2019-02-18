// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hamitao.kids.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ContentTreeChildrenActivity_ViewBinding implements Unbinder {
  private ContentTreeChildrenActivity target;

  @UiThread
  public ContentTreeChildrenActivity_ViewBinding(ContentTreeChildrenActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ContentTreeChildrenActivity_ViewBinding(ContentTreeChildrenActivity target, View source) {
    this.target = target;

    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.rv_content_tree_children, "field 'mRecyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ContentTreeChildrenActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecyclerView = null;
  }
}
