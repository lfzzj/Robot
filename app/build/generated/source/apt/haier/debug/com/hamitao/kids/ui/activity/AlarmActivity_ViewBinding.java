// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hamitao.kids.R;
import com.hamitao.kids.widgets.LeftSwipeMenuRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AlarmActivity_ViewBinding implements Unbinder {
  private AlarmActivity target;

  @UiThread
  public AlarmActivity_ViewBinding(AlarmActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AlarmActivity_ViewBinding(AlarmActivity target, View source) {
    this.target = target;

    target.rvAlarm = Utils.findRequiredViewAsType(source, R.id.rv_alarm, "field 'rvAlarm'", LeftSwipeMenuRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AlarmActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvAlarm = null;
  }
}
