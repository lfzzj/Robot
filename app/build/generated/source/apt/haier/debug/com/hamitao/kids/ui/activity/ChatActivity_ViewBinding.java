// Generated code from Butter Knife. Do not modify!
package com.hamitao.kids.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.jiguang.imui.messages.MessageList;
import com.hamitao.kids.R;
import com.hamitao.kids.record.AudioRecordButton;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChatActivity_ViewBinding implements Unbinder {
  private ChatActivity target;

  @UiThread
  public ChatActivity_ViewBinding(ChatActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChatActivity_ViewBinding(ChatActivity target, View source) {
    this.target = target;

    target.btnVoice = Utils.findRequiredViewAsType(source, R.id.btn_voice, "field 'btnVoice'", AudioRecordButton.class);
    target.btnVideo = Utils.findRequiredViewAsType(source, R.id.btn_video, "field 'btnVideo'", Button.class);
    target.msgList = Utils.findRequiredViewAsType(source, R.id.msg_list, "field 'msgList'", MessageList.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChatActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnVoice = null;
    target.btnVideo = null;
    target.msgList = null;
  }
}
