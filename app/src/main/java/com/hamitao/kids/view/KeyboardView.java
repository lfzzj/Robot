package com.hamitao.kids.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hamitao.framework.R;
import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.keyboard.KeyboardAdapter;
import com.hamitao.kids.keyboard.KeyboardUtil;

import java.lang.reflect.Method;
import java.util.List;

public class KeyboardView extends LinearLayout {
    private static final String TAG = "KeyboardView";
    private Context context;
    //    private TextView tvKey;
    private EditText etKey;
    private RecyclerView rvContent;
    private RelativeLayout rlDel;
    private RelativeLayout rlChange;
    private RelativeLayout rlCase;
    private RelativeLayout rlEnter;
    private ImageView ivCase;
    private TextView tvChange;

    private int curChange = 0;//默认是字母

    private boolean isCapital = false;//是否是大写，默认是小写

    private String keyWord;
    private String passWd;//最终密码

    private KeyboardAdapter mAdapter;
    private List<String> mDatas;
    private int layout_item;

    public KeyboardView(Context context) {
        super(context);
    }

    public KeyboardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initData();
    }

    public KeyboardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initData();
    }

    private void initData() {
        int layout = R.layout.view_keyboard;
        layout_item = R.layout.item_keyboard;
        View view = LayoutInflater.from(context).inflate(layout, this);
//        tvKey = view.findViewById(R.id.tv_key);
        etKey = view.findViewById(R.id.et_key);
        disableShowSoftInput(etKey);
        //将输入法切换到英文
        etKey.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        rvContent = view.findViewById(R.id.rv_content);

        rlDel = view.findViewById(R.id.rl_del);
        rlChange = view.findViewById(R.id.rl_change);
        rlCase = view.findViewById(R.id.rl_case);
        rlEnter = view.findViewById(R.id.rl_enter);
        ivCase = view.findViewById(R.id.iv_case);
        tvChange = view.findViewById(R.id.tv_change);

        rlDel.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                return false;
            }
        });
        initListener();
        initAdapter();
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

    private void initAdapter() {
        mDatas = KeyboardUtil.getLowercaseLetters();
        // 竖直方向的网格样式，每行四个Item
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 4);
        rvContent.setLayoutManager(gridLayoutManager);
        mAdapter = new KeyboardAdapter(rvContent, layout_item);
        rvContent.setAdapter(mAdapter);
        mAdapter.setData(mDatas);
        mAdapter.setOnClickListener(new KeyboardAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                Logger.d(TAG, "=====" + mDatas.get(position));
                keyWord = mDatas.get(position);
//                passWd = passWd + keyWord;
//                etKey.setText(passWd);
                insertValue(keyWord);
            }
        });
    }

    private void initListener() {
        rlDel.setOnClickListener(mOnClickListener);
        rlChange.setOnClickListener(mOnClickListener);
        rlCase.setOnClickListener(mOnClickListener);
        rlEnter.setOnClickListener(mOnClickListener);
    }

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            int i = view.getId();
            if (i == R.id.rl_del) {//删除
                delValue();
            } else if (i == R.id.rl_change) {//切换（字母/数字/符号）
                change();
            } else if (i == R.id.rl_case) {//大小写切换
                caseSwitch();
            } else if (i == R.id.rl_enter) {//确定
                enter();
            }
        }
    };

    private void enter() {
        if (clickListener != null) {
            clickListener.onClick(etKey.getText().toString());
        }
    }

    public String getEditPsw() {
        return etKey.getText().toString();
    }

    /**
     * 大小写切换
     */
    private void caseSwitch() {
        curChange = 0;
        tvChange.setText(context.getResources().getString(R.string.letter));
        isCapital = !isCapital;
        ivCase.setImageResource(isCapital ? R.drawable.icon_capital : R.drawable.icon_lowercase);
        refreshData(KeyboardUtil.getCaseData(isCapital));
    }


    /**
     * 切换（字母/数字/符号）
     */
    private void change() {
        curChange = curChange + 1;
        if (curChange > 2) {
            curChange = 0;
        }
        Logger.d(TAG, "curChange=" + curChange);
        int resId = R.string.letter;
        if (curChange == 0) {
            resId = R.string.letter;
        } else if (curChange == 1) {
            resId = R.string.num;
        } else if (curChange == 2) {
            resId = R.string.symbol;
        }
        tvChange.setText(context.getResources().getString(resId));
        refreshData(KeyboardUtil.getCurData(curChange, isCapital));
    }

    /**
     * 刷新数据
     *
     * @param datas
     */
    public void refreshData(List<String> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        mAdapter.setData(mDatas);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 设置键盘的初始值
     *
     * @param key
     */
    public void setEditKey(String key) {
        etKey.setText(key);
        etKey.setSelection(etKey.getText().length());
    }

    /**
     * 插入字符
     *
     * @param key
     */
    private void insertValue(String key) {
        int index = etKey.getSelectionStart();
        Editable editable = etKey.getText();
        editable.insert(index, key);
    }

    /**
     * 删除字符
     */
    private void delValue() {
        int index = etKey.getSelectionStart();
        if (index == 0) {
            return;
        }
        Editable editable = etKey.getText();
        editable.delete(index - 1, index);
    }


    private onClickListener clickListener;

    public interface onClickListener {
        void onClick(String keyValue);
    }

    public void setOnClickListener(onClickListener clickListener) {
        this.clickListener = clickListener;
    }

}
