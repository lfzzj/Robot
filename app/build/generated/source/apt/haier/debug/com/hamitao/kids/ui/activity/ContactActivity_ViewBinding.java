// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hamitao.kids.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ContactActivity_ViewBinding implements Unbinder {
  private ContactActivity target;

  private View view2131755208;

  private View view2131755209;

  @UiThread
  public ContactActivity_ViewBinding(ContactActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ContactActivity_ViewBinding(final ContactActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.rb_customer, "field 'rbCustomer' and method 'onClick'");
    target.rbCustomer = Utils.castView(view, R.id.rb_customer, "field 'rbCustomer'", RadioButton.class);
    view2131755208 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rb_call_phone, "field 'rbCallPhone' and method 'onClick'");
    target.rbCallPhone = Utils.castView(view, R.id.rb_call_phone, "field 'rbCallPhone'", RadioButton.class);
    view2131755209 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.rlBg = Utils.findRequiredViewAsType(source, R.id.rl_bg, "field 'rlBg'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ContactActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rbCustomer = null;
    target.rbCallPhone = null;
    target.rlBg = null;

    view2131755208.setOnClickListener(null);
    view2131755208 = null;
    view2131755209.setOnClickListener(null);
    view2131755209 = null;
  }
}
