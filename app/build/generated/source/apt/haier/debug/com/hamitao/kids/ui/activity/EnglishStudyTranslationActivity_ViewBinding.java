// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hamitao.kids.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EnglishStudyTranslationActivity_ViewBinding implements Unbinder {
  private EnglishStudyTranslationActivity target;

  @UiThread
  public EnglishStudyTranslationActivity_ViewBinding(EnglishStudyTranslationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EnglishStudyTranslationActivity_ViewBinding(EnglishStudyTranslationActivity target,
      View source) {
    this.target = target;

    target.ivHoldDownSpeak = Utils.findRequiredViewAsType(source, R.id.iv_hold_down_speak, "field 'ivHoldDownSpeak'", Button.class);
    target.ivVoiceRight = Utils.findRequiredViewAsType(source, R.id.iv_voice_right, "field 'ivVoiceRight'", ImageView.class);
    target.ivVoiceLeft = Utils.findRequiredViewAsType(source, R.id.iv_voice_left, "field 'ivVoiceLeft'", ImageView.class);
    target.tvTranslation = Utils.findRequiredViewAsType(source, R.id.tv_translation_e, "field 'tvTranslation'", TextView.class);
    target.tvOriginal = Utils.findRequiredViewAsType(source, R.id.tv_translation_c, "field 'tvOriginal'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EnglishStudyTranslationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivHoldDownSpeak = null;
    target.ivVoiceRight = null;
    target.ivVoiceLeft = null;
    target.tvTranslation = null;
    target.tvOriginal = null;
  }
}
