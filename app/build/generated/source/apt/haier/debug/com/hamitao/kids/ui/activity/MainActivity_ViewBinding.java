// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hamitao.kids.R;
import com.hamitao.kids.view.DataTimeView;
import com.hamitao.kids.view.StatusBarView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131755219;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.statusBarView = Utils.findRequiredViewAsType(source, R.id.status_bar, "field 'statusBarView'", StatusBarView.class);
    target.statusDataTime = Utils.findRequiredViewAsType(source, R.id.status_data_time, "field 'statusDataTime'", DataTimeView.class);
    view = Utils.findRequiredView(source, R.id.rl_home, "method 'onClick'");
    view2131755219 = view;
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
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.statusBarView = null;
    target.statusDataTime = null;

    view2131755219.setOnClickListener(null);
    view2131755219 = null;
  }
}
