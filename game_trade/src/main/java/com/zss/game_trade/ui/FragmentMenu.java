package com.zss.game_trade.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zss.game_trade.R;
import com.zss.game_trade.ui.basic.BaseFragment;

/**
 * 选择大区
 * Created by Administrator on 2016/11/10.
 */
public class FragmentMenu extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        View menu = view.findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (closeMenu != null) {
                    closeMenu.closeMenu();
                }
            }
        });
        return view;
    }


    private CloseMenu closeMenu;

    public void setCloseMenu(CloseMenu closeMenu) {
        this.closeMenu = closeMenu;
    }

    public interface CloseMenu {
        void closeMenu();
    }
}
