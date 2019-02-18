// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hamitao.kids.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VideoChatP2PActivity_ViewBinding implements Unbinder {
  private VideoChatP2PActivity target;

  private View view2131755280;

  @UiThread
  public VideoChatP2PActivity_ViewBinding(VideoChatP2PActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VideoChatP2PActivity_ViewBinding(final VideoChatP2PActivity target, View source) {
    this.target = target;

    View view;
    target.layoutVideo = Utils.findRequiredViewAsType(source, R.id.layout_video, "field 'layoutVideo'", LinearLayout.class);
    target.layoutPrvw = Utils.findRequiredViewAsType(source, R.id.layout_prvw, "field 'layoutPrvw'", LinearLayout.class);
    target.imageHead = Utils.findRequiredViewAsType(source, R.id.image_head, "field 'imageHead'", ImageView.class);
    target.chronometer = Utils.findRequiredViewAsType(source, R.id.timer, "field 'chronometer'", Chronometer.class);
    target.rlHangUpFrame = Utils.findRequiredViewAsType(source, R.id.rl_hang_up_frame, "field 'rlHangUpFrame'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.iv_refuse_phone, "method 'onClick'");
    view2131755280 = view;
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
    VideoChatP2PActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.layoutVideo = null;
    target.layoutPrvw = null;
    target.imageHead = null;
    target.chronometer = null;
    target.rlHangUpFrame = null;

    view2131755280.setOnClickListener(null);
    view2131755280 = null;
  }
}
