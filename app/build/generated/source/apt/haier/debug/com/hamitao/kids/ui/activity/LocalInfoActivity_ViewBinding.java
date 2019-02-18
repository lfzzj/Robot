// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hamitao.kids.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LocalInfoActivity_ViewBinding implements Unbinder {
  private LocalInfoActivity target;

  @UiThread
  public LocalInfoActivity_ViewBinding(LocalInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LocalInfoActivity_ViewBinding(LocalInfoActivity target, View source) {
    this.target = target;

    target.tvLocalInfo = Utils.findRequiredViewAsType(source, R.id.tv_local_info, "field 'tvLocalInfo'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LocalInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvLocalInfo = null;
  }
}
