// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hamitao.kids.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ParentsAppDownloadActivity_ViewBinding implements Unbinder {
  private ParentsAppDownloadActivity target;

  private View view2131755229;

  @UiThread
  public ParentsAppDownloadActivity_ViewBinding(ParentsAppDownloadActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ParentsAppDownloadActivity_ViewBinding(final ParentsAppDownloadActivity target,
      View source) {
    this.target = target;

    View view;
    target.ivCode = Utils.findRequiredViewAsType(source, R.id.iv_parents_app_download_code, "field 'ivCode'", ImageView.class);
    target.tvHint = Utils.findRequiredViewAsType(source, R.id.tv_parents_app_download, "field 'tvHint'", TextView.class);
    target.tvBtn = Utils.findRequiredViewAsType(source, R.id.tv_parents_app_download_next, "field 'tvBtn'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_parents_app_download_next, "method 'onClick'");
    view2131755229 = view;
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
    ParentsAppDownloadActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivCode = null;
    target.tvHint = null;
    target.tvBtn = null;

    view2131755229.setOnClickListener(null);
    view2131755229 = null;
  }
}
