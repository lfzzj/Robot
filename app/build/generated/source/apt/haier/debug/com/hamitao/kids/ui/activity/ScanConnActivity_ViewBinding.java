// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hamitao.kids.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ScanConnActivity_ViewBinding implements Unbinder {
  private ScanConnActivity target;

  private View view2131755237;

  @UiThread
  public ScanConnActivity_ViewBinding(ScanConnActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ScanConnActivity_ViewBinding(final ScanConnActivity target, View source) {
    this.target = target;

    View view;
    target.tvDesc = Utils.findRequiredViewAsType(source, R.id.tv_desc, "field 'tvDesc'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_scan, "field 'btnScan' and method 'onClick'");
    target.btnScan = Utils.castView(view, R.id.btn_scan, "field 'btnScan'", Button.class);
    view2131755237 = view;
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
    ScanConnActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvDesc = null;
    target.btnScan = null;

    view2131755237.setOnClickListener(null);
    view2131755237 = null;
  }
}
