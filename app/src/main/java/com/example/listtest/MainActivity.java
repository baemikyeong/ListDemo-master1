package com.example.listtest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
//뭔가 허전
    public static final int HDR_POS1 = 0;
    public static final int HDR_POS2 = 6;
    public static final String[] LIST = { "Viewer Setting", "배경색",
        "밝기", "글꼴", "블루라이트 조절", "페이지 넘기는 방식",
        "Personalize", "Eye Personalize"};
    public static final String[] SUBTEXTS = { null, "문서를 볼 때의 배경색을 지정합니다.",
        "블링클링 사용 시, 밝기를 조정합니다. ", "문서를 볼 때의 글꼴을 지정합니다. ", "블루라이트의 정도를 조정합니다. ", "문서를 볼 때, 슬라이딩 페이지와 페이저 방식 중에 선택합니다. ",
        null, "눈 크기와 눈 깜박임 시간을 개인에 맞게 조정합니다." };

    private static final Integer LIST_HEADER = 0;
    private static final Integer LIST_ITEM = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = (ListView)findViewById(R.id.listView1);
        lv.setAdapter(new MyListAdapter(this));
    }

    public void onClickIn (View v ){

        if(LIST.equals(LIST[3]) == true)
        Toast.makeText(this, "안뇽", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "바이", Toast.LENGTH_SHORT).show();


    }

    private class MyListAdapter extends BaseAdapter {
        public MyListAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return LIST.length;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return true;
        }

        @Override
        public boolean isEnabled(int position) {
            return true;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            String headerText = getHeader(position);
            if(headerText != null) {

                View item = convertView;
                if(convertView == null || convertView.getTag() == LIST_ITEM) {

                    item = LayoutInflater.from(mContext).inflate(
                            R.layout.lv_header_layout, parent, false);
                    item.setTag(LIST_HEADER);

                }

                TextView headerTextView = (TextView)item.findViewById(R.id.lv_list_hdr);
                headerTextView.setText(headerText);
                return item;
            }

            View item = convertView;
            if(convertView == null || convertView.getTag() == LIST_HEADER) {
                item = LayoutInflater.from(mContext).inflate(
                        R.layout.lv_layout, parent, false);
                item.setTag(LIST_ITEM);
            }

            TextView header = (TextView)item.findViewById(R.id.lv_item_header);
            header.setText(LIST[position % LIST.length]);

            TextView subtext = (TextView)item.findViewById(R.id.lv_item_subtext);
            subtext.setText(SUBTEXTS[position % SUBTEXTS.length]);

            //Set last divider in a sublist invisible
            View divider = item.findViewById(R.id.item_separator);
            if(position == HDR_POS2 -1) {
                divider.setVisibility(View.INVISIBLE);
            }


            return item;
        }

        private String getHeader(int position) {

            if(position == HDR_POS1  || position == HDR_POS2) {
                return LIST[position];
            }

            return null;
        }

        private final Context mContext;
    }

}
