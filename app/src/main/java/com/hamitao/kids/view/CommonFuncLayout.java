package com.hamitao.kids.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.kids.R;
import com.hamitao.kids.app.MyApp;


/**
 * @data on 2018/6/5 16:54
 * @describe:
 */

public class CommonFuncLayout extends LinearLayout {
    private ImageView ivIcon;
    private TextView tvName;
    private Context mContext;


    public CommonFuncLayout(Context context) {
        super(context, null);
    }

    public CommonFuncLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_common_func, this);
        ivIcon = findViewById(R.id.iv_icon);
        tvName = findViewById(R.id.tv_name);
    }

    public void setCommonInfo(int icon, String name) {
        ivIcon.setBackgroundResource(icon);
        tvName.setText(name);
    }

    /**
     * 设置文本不可点击的颜色
     */
    public void setTextNotClickColor() {
        tvName.setTextColor(mContext.getResources().getColor(R.color.gray));
    }


}
