// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hamitao.kids.R;
import com.hamitao.kids.manager.MediaManager.EmojiView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WakeUpActivity_ViewBinding implements Unbinder {
  private WakeUpActivity target;

  @UiThread
  public WakeUpActivity_ViewBinding(WakeUpActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WakeUpActivity_ViewBinding(WakeUpActivity target, View source) {
    this.target = target;

    target.emojiView = Utils.findRequiredViewAsType(source, R.id.view_emoji, "field 'emojiView'", EmojiView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WakeUpActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.emojiView = null;
  }
}
