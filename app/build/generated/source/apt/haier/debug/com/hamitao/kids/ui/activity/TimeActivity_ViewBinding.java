// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hamitao.kids.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TimeActivity_ViewBinding implements Unbinder {
  private TimeActivity target;

  private View view2131755268;

  private View view2131755269;

  @UiThread
  public TimeActivity_ViewBinding(TimeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TimeActivity_ViewBinding(final TimeActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_switch_data_time, "field 'btnSwitchDataTime' and method 'onClick'");
    target.btnSwitchDataTime = Utils.castView(view, R.id.btn_switch_data_time, "field 'btnSwitchDataTime'", ImageView.class);
    view2131755268 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.rlAutoDataTime = Utils.findRequiredViewAsType(source, R.id.rl_auto_data_time, "field 'rlAutoDataTime'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_alarm, "method 'onClick'");
    view2131755269 = view;
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
    TimeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnSwitchDataTime = null;
    target.rlAutoDataTime = null;

    view2131755268.setOnClickListener(null);
    view2131755268 = null;
    view2131755269.setOnClickListener(null);
    view2131755269 = null;
  }
}
