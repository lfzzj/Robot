// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hamitao.kids.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CameraActivity_ViewBinding implements Unbinder {
  private CameraActivity target;

  @UiThread
  public CameraActivity_ViewBinding(CameraActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CameraActivity_ViewBinding(CameraActivity target, View source) {
    this.target = target;

    target.takePicture = Utils.findRequiredViewAsType(source, R.id.take_picture, "field 'takePicture'", Button.class);
    target.focusIndex = Utils.findRequiredView(source, R.id.focus_index, "field 'focusIndex'");
    target.surfaceView = Utils.findRequiredViewAsType(source, R.id.camera_surfaceView, "field 'surfaceView'", SurfaceView.class);
    target.ivPhotoPreview = Utils.findRequiredViewAsType(source, R.id.iv_photo_preview, "field 'ivPhotoPreview'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CameraActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.takePicture = null;
    target.focusIndex = null;
    target.surfaceView = null;
    target.ivPhotoPreview = null;
  }
}
