// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hamitao.kids.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AlbumActivity_ViewBinding implements Unbinder {
  private AlbumActivity target;

  @UiThread
  public AlbumActivity_ViewBinding(AlbumActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AlbumActivity_ViewBinding(AlbumActivity target, View source) {
    this.target = target;

    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.rv_album, "field 'mRecyclerView'", RecyclerView.class);
    target.btnPhotograph = Utils.findRequiredViewAsType(source, R.id.btn_photograph, "field 'btnPhotograph'", Button.class);
    target.rlPhotoDel = Utils.findRequiredViewAsType(source, R.id.rl_photo_del, "field 'rlPhotoDel'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AlbumActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecyclerView = null;
    target.btnPhotograph = null;
    target.rlPhotoDel = null;
  }
}
