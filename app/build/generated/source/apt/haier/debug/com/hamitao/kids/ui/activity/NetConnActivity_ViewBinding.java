// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hamitao.kids.R;
import com.hamitao.kids.widgets.NetConnWayView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NetConnActivity_ViewBinding implements Unbinder {
  private NetConnActivity target;

  private View view2131755224;

  private View view2131755225;

  private View view2131755226;

  private View view2131755221;

  @UiThread
  public NetConnActivity_ViewBinding(NetConnActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public NetConnActivity_ViewBinding(final NetConnActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.view_scan_conn, "field 'viewScanConn' and method 'onClick'");
    target.viewScanConn = Utils.castView(view, R.id.view_scan_conn, "field 'viewScanConn'", NetConnWayView.class);
    view2131755224 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.view_manual_conn, "field 'viewManualConn' and method 'onClick'");
    target.viewManualConn = Utils.castView(view, R.id.view_manual_conn, "field 'viewManualConn'", NetConnWayView.class);
    view2131755225 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.view_mobile_network, "field 'viewMobileConn' and method 'onClick'");
    target.viewMobileConn = Utils.castView(view, R.id.view_mobile_network, "field 'viewMobileConn'", NetConnWayView.class);
    view2131755226 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvNetName = Utils.findRequiredViewAsType(source, R.id.tv_net_name, "field 'tvNetName'", TextView.class);
    target.rlBg = Utils.findRequiredViewAsType(source, R.id.rl_bg, "field 'rlBg'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_skip, "field 'tvSkip' and method 'onClick'");
    target.tvSkip = Utils.castView(view, R.id.tv_skip, "field 'tvSkip'", TextView.class);
    view2131755221 = view;
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
    NetConnActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.viewScanConn = null;
    target.viewManualConn = null;
    target.viewMobileConn = null;
    target.tvNetName = null;
    target.rlBg = null;
    target.tvSkip = null;

    view2131755224.setOnClickListener(null);
    view2131755224 = null;
    view2131755225.setOnClickListener(null);
    view2131755225 = null;
    view2131755226.setOnClickListener(null);
    view2131755226 = null;
    view2131755221.setOnClickListener(null);
    view2131755221 = null;
  }
}
