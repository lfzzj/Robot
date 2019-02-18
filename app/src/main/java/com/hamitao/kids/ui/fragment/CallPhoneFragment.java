package com.hamitao.kids.ui.fragment;


import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.hamitao.framework.enums.FuncTitle;
import com.hamitao.framework.factory.SpecialCharSequenceMgr;
import com.hamitao.framework.utils.MobileUtil;
import com.hamitao.kids.R;
import com.hamitao.kids.ui.activity.ContactActivity;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @data on 2018/6/7 17:36
 * @describe: 拨号
 */

public class CallPhoneFragment extends Fragment {
    @BindView(R.id.tv_phone_num)
    EditText tvPhoneNum;
    private ContactActivity activity;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (ContactActivity) getActivity();
        int res = R.layout.fragment_call_phone_common;
        View view = inflater.inflate(res, container, false);
        unbinder = ButterKnife.bind(this, view);
        //将输入法切换到英文
        tvPhoneNum.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        disableShowSoftInput(tvPhoneNum);
        //将输入法切换到英文
        tvPhoneNum.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        return view;
    }


    public void disableShowSoftInput(EditText editText) {
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            editText.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method method;
            try {
                method = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(editText, false);
            } catch (Exception e) {
            }
        }
    }

    private String keyWord;

    @OnClick({R.id.btn_one, R.id.btn_two, R.id.btn_three, R.id.btn_four, R.id.btn_five, R.id.btn_six,
            R.id.btn_seven, R.id.btn_eight, R.id.btn_nine, R.id.btn_star, R.id.btn_zero, R.id.btn_pound,
            R.id.btn_call, R.id.btn_del
    })
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one:
                keyWord = "1";
                break;
            case R.id.btn_two:
                keyWord = "2";
                break;
            case R.id.btn_three:
                keyWord = "3";
                break;
            case R.id.btn_four:
                keyWord = "4";
                break;
            case R.id.btn_five:
                keyWord = "5";
                break;
            case R.id.btn_six:
                keyWord = "6";
                break;
            case R.id.btn_seven:
                keyWord = "7";
                break;
            case R.id.btn_eight:
                keyWord = "8";
                break;
            case R.id.btn_nine:
                keyWord = "9";
                break;
            case R.id.btn_star:
                keyWord = "*";
                break;
            case R.id.btn_zero:
                keyWord = "0";
                break;
            case R.id.btn_pound:
                keyWord = "#";
                break;
            case R.id.btn_call:
                keyWord = "";
                phoneNum = tvPhoneNum.getText().toString();
                if (phoneNum != null && phoneNum.length() != 0) {
                    if ("00000000".equals(phoneNum)) {
                        startDevelopmentActivity();
                        return;
                    }
                    if (!MobileUtil.ishasSimCard(getActivity())) {
//                        ((ContactActivity) getActivity()).speakTTS(InstructUtils.respondNotInstallSim(), TTSEngine.TTS_FLAG_COMP_NO_RESULT);
                        ((ContactActivity) getActivity()).speak(FuncTitle.CONTENT_VOICE_HINT_INSTALL_SIM.toString());
                    }
                    ((ContactActivity) getActivity()).publicPresenter.callPhone(phoneNum);
                }
                break;
            case R.id.btn_del:
                keyWord = "";
                delect();
                break;
        }
        if (keyWord != "") {
            setPhoneNum(keyWord);
        }
    }

//    private void isDebug(boolean isEnable) {
//        if (!isEnable) {
//            ToastUtil.showToast(getContext(), "调试模式关闭");
////            Settings.Secure.putInt(getActivity().getContentResolver(), Settings.Secure.ADB_ENABLED, 0);
//            startDevelopmentActivity();
//        } else {
//            //判断是否打开调试模式
//            boolean enableAdb = (Settings.Secure.getInt(getActivity().getContentResolver(), Settings.Secure.ADB_ENABLED, 0) > 0);
//            if (!enableAdb) {
//                ToastUtil.showToast(getContext(), "调试模式打开");
//                Settings.Secure.putInt(getActivity().getContentResolver(), Settings.Secure.ADB_ENABLED, 1);
//            }
//        }
//    }

    /**
     * 打开开发者模式界面
     */
    private void startDevelopmentActivity() {
        try {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
            startActivity(intent);
        } catch (Exception e) {
            try {
                ComponentName componentName = new ComponentName("com.android.settings", "com.android.settings.DevelopmentSettings");
                Intent intent = new Intent();
                intent.setComponent(componentName);
                intent.setAction("android.intent.action.View");
                startActivity(intent);
            } catch (Exception e1) {
                try {
                    Intent intent = new Intent("com.android.settings.APPLICATION_DEVELOPMENT_SETTINGS");//部分小米手机采用这种方式跳转
                    startActivity(intent);
                } catch (Exception e2) {

                }

            }
        }
    }


    private String phoneNum = "";

    public void setPhoneNum(String keyWord) {
        int index = tvPhoneNum.getSelectionStart();
        Editable editable = tvPhoneNum.getText();
        editable.insert(index, keyWord);
        checkEnterFactoryMode(phoneNum);
    }

    /**
     * 检测进入工厂模式
     *
     * @param phoneNum
     */
    private void checkEnterFactoryMode(String phoneNum) {
        SpecialCharSequenceMgr.handleChars(activity, phoneNum);
    }

    public void delect() {

        int index = tvPhoneNum.getSelectionStart();
        if (index == 0) {
            return;
        }
        Editable editable = tvPhoneNum.getText();
        editable.delete(index - 1, index);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
