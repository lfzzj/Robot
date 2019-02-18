package com.hamitao.kids.manager.play;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hamitao.kids.R;

public class RefreshView extends RelativeLayout {
    private static String TAG = "RefreshView";
    private Context mContext;

    public RefreshView(Context context) {
        super(context);
    }

    public RefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public RefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_refresh, this);
        Button btnRefresh = view.findViewById(R.id.btn_refresh);
        btnRefresh.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onRefreshClickListener != null) {
                    onRefreshClickListener.onClick();
                }
            }
        });
    }

    private OnRefreshClickListener onRefreshClickListener;

    public void setOnRefreshClickListener(OnRefreshClickListener onRefreshClickListener) {
        this.onRefreshClickListener = onRefreshClickListener;
    }

    public interface OnRefreshClickListener {
        void onClick();
    }

}
