// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hamitao.kids.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SystemControlActivity_ViewBinding implements Unbinder {
  private SystemControlActivity target;

  private View view2131755260;

  private View view2131755264;

  @UiThread
  public SystemControlActivity_ViewBinding(SystemControlActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SystemControlActivity_ViewBinding(final SystemControlActivity target, View source) {
    this.target = target;

    View view;
    target.seekBar = Utils.findRequiredViewAsType(source, R.id.seekbar, "field 'seekBar'", SeekBar.class);
    target.tvCurValue = Utils.findRequiredViewAsType(source, R.id.tv_cur_value, "field 'tvCurValue'", TextView.class);
    target.ivSysIcon = Utils.findRequiredViewAsType(source, R.id.iv_sys_icon, "field 'ivSysIcon'", ImageView.class);
    target.rlSysIcon = Utils.findRequiredViewAsType(source, R.id.rl_sys_icon, "field 'rlSysIcon'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.rl_minus, "field 'rlMinus' and method 'onClick'");
    target.rlMinus = Utils.castView(view, R.id.rl_minus, "field 'rlMinus'", RelativeLayout.class);
    view2131755260 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_plus, "field 'rlPlus' and method 'onClick'");
    target.rlPlus = Utils.castView(view, R.id.rl_plus, "field 'rlPlus'", RelativeLayout.class);
    view2131755264 = view;
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
    SystemControlActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.seekBar = null;
    target.tvCurValue = null;
    target.ivSysIcon = null;
    target.rlSysIcon = null;
    target.rlMinus = null;
    target.rlPlus = null;

    view2131755260.setOnClickListener(null);
    view2131755260 = null;
    view2131755264.setOnClickListener(null);
    view2131755264 = null;
  }
}
