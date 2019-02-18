// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hamitao.kids.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WifiActivity_ViewBinding implements Unbinder {
  private WifiActivity target;

  @UiThread
  public WifiActivity_ViewBinding(WifiActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WifiActivity_ViewBinding(WifiActivity target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.rv_wifi_list, "field 'recyclerView'", RecyclerView.class);
    target.btnSwitchWifi = Utils.findRequiredViewAsType(source, R.id.btn_switch_wifi_head, "field 'btnSwitchWifi'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WifiActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
    target.btnSwitchWifi = null;
  }
}
