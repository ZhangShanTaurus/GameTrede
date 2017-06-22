package com.zss.trade.ui.base;

import com.zss.trade.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Activity基类
 */
public abstract class BaseFragmentActivity extends FragmentActivity implements View.OnClickListener {
    protected final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        initTitleView();
    }

    protected abstract int getContentView();

    /**
     * 获取标题
     *
     * @return
     */
    protected abstract String getTitleString();

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.title_img_back:
                finish();
                break;
        }
    }

    private void initTitleView() {
        TextView titleText = (TextView) findViewById(R.id.title_text);
        if (titleText != null) {
            titleText.setText(getTitleString());
        }
        ImageView backImage = (ImageView) findViewById(R.id.title_img_back);
        if (backImage != null) {
            backImage.setOnClickListener(this);
        }
    }
}
