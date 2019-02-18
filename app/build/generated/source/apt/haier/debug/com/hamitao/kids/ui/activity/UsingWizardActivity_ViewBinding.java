// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hamitao.kids.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UsingWizardActivity_ViewBinding implements Unbinder {
  private UsingWizardActivity target;

  @UiThread
  public UsingWizardActivity_ViewBinding(UsingWizardActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UsingWizardActivity_ViewBinding(UsingWizardActivity target, View source) {
    this.target = target;

    target.mTv = Utils.findRequiredViewAsType(source, R.id.tv_wizard, "field 'mTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    UsingWizardActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTv = null;
  }
}
