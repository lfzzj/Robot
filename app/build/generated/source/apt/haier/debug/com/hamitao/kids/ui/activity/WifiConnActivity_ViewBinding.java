// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hamitao.kids.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WifiConnActivity_ViewBinding implements Unbinder {
  private WifiConnActivity target;

  @UiThread
  public WifiConnActivity_ViewBinding(WifiConnActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WifiConnActivity_ViewBinding(WifiConnActivity target, View source) {
    this.target = target;

    target.etWifiPass = Utils.findRequiredViewAsType(source, R.id.et_wifi_pass, "field 'etWifiPass'", EditText.class);
    target.tvConnWifiName = Utils.findRequiredViewAsType(source, R.id.tv_conn_wifi_name, "field 'tvConnWifiName'", TextView.class);
    target.tvCanael = Utils.findRequiredViewAsType(source, R.id.tv_wifi_conn_cancel, "field 'tvCanael'", Button.class);
    target.tvComfirm = Utils.findRequiredViewAsType(source, R.id.tv_wifi_conn_comfirm, "field 'tvComfirm'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WifiConnActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etWifiPass = null;
    target.tvConnWifiName = null;
    target.tvCanael = null;
    target.tvComfirm = null;
  }
}
