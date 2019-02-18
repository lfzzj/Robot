// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hamitao.kids.R;
import com.hamitao.kids.manager.play.Controller;
import com.hamitao.kids.manager.play.ImgPlayer;
import com.hamitao.kids.manager.play.MediaPlayer;
import com.hamitao.kids.manager.play.RefreshView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PlayActivity_ViewBinding implements Unbinder {
  private PlayActivity target;

  @UiThread
  public PlayActivity_ViewBinding(PlayActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PlayActivity_ViewBinding(PlayActivity target, View source) {
    this.target = target;

    target.mMediaPlayer = Utils.findRequiredViewAsType(source, R.id.mediaplayer, "field 'mMediaPlayer'", MediaPlayer.class);
    target.mRefreshView = Utils.findRequiredViewAsType(source, R.id.view_refresh, "field 'mRefreshView'", RefreshView.class);
    target.mController = Utils.findRequiredViewAsType(source, R.id.controller, "field 'mController'", Controller.class);
    target.imgPlayer = Utils.findRequiredViewAsType(source, R.id.imageplayer, "field 'imgPlayer'", ImgPlayer.class);
    target.rlBg = Utils.findRequiredViewAsType(source, R.id.rl_bg, "field 'rlBg'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PlayActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mMediaPlayer = null;
    target.mRefreshView = null;
    target.mController = null;
    target.imgPlayer = null;
    target.rlBg = null;
  }
}
