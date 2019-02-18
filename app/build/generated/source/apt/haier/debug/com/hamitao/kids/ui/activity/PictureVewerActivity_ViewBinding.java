// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hamitao.kids.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PictureVewerActivity_ViewBinding implements Unbinder {
  private PictureVewerActivity target;

  @UiThread
  public PictureVewerActivity_ViewBinding(PictureVewerActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PictureVewerActivity_ViewBinding(PictureVewerActivity target, View source) {
    this.target = target;

    target.ivPicViewer = Utils.findRequiredViewAsType(source, R.id.iv_pic_viewer, "field 'ivPicViewer'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PictureVewerActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivPicViewer = null;
  }
}
