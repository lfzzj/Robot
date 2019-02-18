// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hamitao.kids.R;
import com.hamitao.kids.view.CommonFuncLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingActivity_ViewBinding implements Unbinder {
  private SettingActivity target;

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target, View source) {
    this.target = target;

    target.rlAppDownload = Utils.findRequiredViewAsType(source, R.id.rl_app_download, "field 'rlAppDownload'", CommonFuncLayout.class);
    target.rlOperatingVideo = Utils.findRequiredViewAsType(source, R.id.rl_operating_video, "field 'rlOperatingVideo'", CommonFuncLayout.class);
    target.rlWifi = Utils.findRequiredViewAsType(source, R.id.rl_wifi, "field 'rlWifi'", CommonFuncLayout.class);
    target.rlMobile = Utils.findRequiredViewAsType(source, R.id.rl_setting_mobile, "field 'rlMobile'", CommonFuncLayout.class);
    target.rlVolumeSetting = Utils.findRequiredViewAsType(source, R.id.rl_volume_setting, "field 'rlVolumeSetting'", CommonFuncLayout.class);
    target.rlBrightSetting = Utils.findRequiredViewAsType(source, R.id.rl_bright_setting, "field 'rlBrightSetting'", CommonFuncLayout.class);
    target.rlLocalInfo = Utils.findRequiredViewAsType(source, R.id.rl_local_info, "field 'rlLocalInfo'", CommonFuncLayout.class);
    target.rlSettingTurnOff = Utils.findRequiredViewAsType(source, R.id.rl_setting_turn_off, "field 'rlSettingTurnOff'", CommonFuncLayout.class);
    target.rlSettingTime = Utils.findRequiredViewAsType(source, R.id.rl_setting_time, "field 'rlSettingTime'", CommonFuncLayout.class);
    target.rlSettingBellset = Utils.findRequiredViewAsType(source, R.id.rl_setting_bellset, "field 'rlSettingBellset'", CommonFuncLayout.class);
    target.rlWirelessUpgrade = Utils.findRequiredViewAsType(source, R.id.rl_wireless_upgrade, "field 'rlWirelessUpgrade'", CommonFuncLayout.class);
    target.rlSwitchMobile = Utils.findRequiredViewAsType(source, R.id.rl_switch_mobile, "field 'rlSwitchMobile'", RelativeLayout.class);
    target.btnSwitch = Utils.findRequiredViewAsType(source, R.id.btn_switch_wifi, "field 'btnSwitch'", ImageView.class);
    target.btnSwitchMobile = Utils.findRequiredViewAsType(source, R.id.iv_switch_mobile, "field 'btnSwitchMobile'", ImageView.class);
    target.tvConnWifiName = Utils.findRequiredViewAsType(source, R.id.tv_conn_wifi_name, "field 'tvConnWifiName'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlAppDownload = null;
    target.rlOperatingVideo = null;
    target.rlWifi = null;
    target.rlMobile = null;
    target.rlVolumeSetting = null;
    target.rlBrightSetting = null;
    target.rlLocalInfo = null;
    target.rlSettingTurnOff = null;
    target.rlSettingTime = null;
    target.rlSettingBellset = null;
    target.rlWirelessUpgrade = null;
    target.rlSwitchMobile = null;
    target.btnSwitch = null;
    target.btnSwitchMobile = null;
    target.tvConnWifiName = null;
  }
}
