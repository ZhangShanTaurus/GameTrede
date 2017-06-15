package com.zss.trade.ui;

import com.zss.trade.R;
import com.zss.trade.add_goods.AddGoodsActivity;
import com.zss.trade.utils.IntentUtils;
import com.zss.trade.widget.AddGoodsView;
import com.zss.trade.widget.TabIndicatorView;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

/**
 * 主页面
 * Created by Administrator on 2016/11/9.
 */
public class MainActivity extends FragmentActivity implements TabHost.OnTabChangeListener {

    private Class[] fragments = {FragmentGoods.class, FragmentDeal.class, FragmentChart.class};
    /**
     * 底部TabIndicator显示的文字
     */
    private static final TabType[] TABS = {TabType.GOODS, TabType.DEAL, TabType.CHART};
    /**
     * 底部TabIndicator正常时显示的图标
     */
    private static final int ICON__RESOURCE_NORMAL[] = {
            R.drawable.icon_tab_goods_normal, R.drawable.icon_tab_deal_normal, R.drawable.icon_tab_chart_normal};
    /**
     * 底部TabIndicator选中时显示的图标
     */
    private static final int ICON__RESOURCE_CHECKED[] =
            {R.drawable.icon_tab_goods_checked, R.drawable.icon_tab_deal_checked, R.drawable.icon_tab_chart_checked};

    /**
     * 顶部描述View
     */
    private TextView mDescText;
    private FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAddGoodsViews();
        initTabHost();
    }

    private void initAddGoodsViews() {
        mDescText = (TextView) findViewById(R.id.tv_desc);
        final AddGoodsView addGoodsView = (AddGoodsView) findViewById(R.id.add_goods);
        final View view = findViewById(android.R.id.tabhost);
        addGoodsView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        addGoodsView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        int height = view.getMeasuredHeight();
                        addGoodsView.setBottomHeight(height);
                    }
                });
        addGoodsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.intent(MainActivity.this, AddGoodsActivity.class);
            }
        });
    }

    private void initTabHost() {
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, super.getSupportFragmentManager(), R.id.content_layout);
        tabHost.getTabWidget().setDividerDrawable(null);
        tabHost.setOnTabChangedListener(this);
        for (int i = 0; i < fragments.length; i++) {
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(TABS[i].getDesc()).setIndicator(getTabIndicatorView(i));
            tabHost.addTab(tabSpec, fragments[i], null);
        }
        tabHost.setCurrentTabByTag(TABS[0].getDesc());
    }

    @Override
    public void onTabChanged(String tabId) {
        mDescText.setText(tabId);
        TabWidget tabWidget = tabHost.getTabWidget();
        for (int i = 0, count = tabWidget.getTabCount(); i < count; i++) {
            TabIndicatorView tabIndicatorView = (TabIndicatorView) tabWidget.getChildAt(i);
            tabIndicatorView.setTabIndicatorChecked(TextUtils.equals(tabId, TABS[i].getDesc()));
        }
    }

    private View getTabIndicatorView(int index) {
        TabIndicatorView tabIndicatorView = new TabIndicatorView(this);
        tabIndicatorView.setTabText(TABS[index].getDesc());
        tabIndicatorView.setTabImageStateIcon(ICON__RESOURCE_NORMAL[index], ICON__RESOURCE_CHECKED[index]);
        return tabIndicatorView;
    }

    private enum TabType {
        GOODS("物品"),
        DEAL("交易"),
        CHART("统计");

        private String desc;

        TabType(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }
}
