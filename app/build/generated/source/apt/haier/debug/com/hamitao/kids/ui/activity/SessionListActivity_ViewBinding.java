// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hamitao.kids.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SessionListActivity_ViewBinding implements Unbinder {
  private SessionListActivity target;

  private View view2131755213;

  @UiThread
  public SessionListActivity_ViewBinding(SessionListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SessionListActivity_ViewBinding(final SessionListActivity target, View source) {
    this.target = target;

    View view;
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.rv_contacts, "field 'mRecyclerView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.iv_shake, "field 'ivShake' and method 'onClick'");
    target.ivShake = Utils.castView(view, R.id.iv_shake, "field 'ivShake'", ImageView.class);
    view2131755213 = view;
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
    SessionListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecyclerView = null;
    target.ivShake = null;

    view2131755213.setOnClickListener(null);
    view2131755213 = null;
  }
}
