package com.zss.trade.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.zss.trade.GameTradeApp;
import com.zss.trade.R;
import com.zss.trade.entity.User;
import com.zss.trade.gen.UserDao;

import java.util.ArrayList;
import java.util.List;

public class GreenDaoActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    private NoteAdapter adapter;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initViews();
        initAdapter();
        userDao = GameTradeApp.getInstances().getDaoSession().getUserDao();
    }

    protected void initViews() {
        Button addBtn = (Button) findViewById(R.id.add);
        if (addBtn != null) {
            addBtn.setOnClickListener(this);
        }
        Button queryBtn = (Button) findViewById(R.id.query);
        if (queryBtn != null) {
            queryBtn.setOnClickListener(this);
        }
        listView = (ListView) findViewById(R.id.listView);
    }

    private void initAdapter() {
        adapter = new NoteAdapter();
        listView.setAdapter(adapter);
    }

    private void add() {
        User user = new User(null, "hello");
        userDao.insert(user);
    }

    private void query() {
        List<User> list = userDao.loadAll();
        if (list != null) {
            adapter.setData(list);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                add();
                startActivity(new Intent(GreenDaoActivity.this, ChartActivity.class));
                break;
            case R.id.query:
                query();
                break;
        }
    }


    static class NoteAdapter extends BaseAdapter {
        private List<User> list = new ArrayList<>();

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view == null) {
                holder = new ViewHolder();
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_note_item, viewGroup, false);
                holder.text = (TextView) view.findViewById(R.id.text);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            holder.text.setText(list.get(i).getName());
            return view;
        }

        private void setData(List<User> list) {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }

        static class ViewHolder {
            TextView text;
        }
    }
}
