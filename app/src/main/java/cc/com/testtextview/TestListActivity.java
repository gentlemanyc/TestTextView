package cc.com.testtextview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuanChao on 2017/11/7.
 */

public class TestListActivity extends AppCompatActivity {
    /**
     * 最多展示3行。
     */
    private static final int LINES = 3;
    /**
     * 数据
     */
    private List<DataModel> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list);
        RecyclerView mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        String temp = "最多显示3行。";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            sb.append(temp);
            data.add(new DataModel(null, sb.toString()));
        }
        mRecyclerView.setAdapter(new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new RecyclerView.ViewHolder(LayoutInflater.from(TestListActivity.this).inflate(R.layout.activity_main, parent, false)) {
                };
            }

            @Override
            public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
                holder.itemView.findViewById(R.id.btn_list_demo).setVisibility(View.GONE);
                final TextView textView = holder.itemView.findViewById(R.id.tv);
                final DataModel model = data.get(position);
                textView.setText(model.text);
                //先将文字的状态保存
                if (model.hasEllipsis == null) {
                    //如果textView.getLayout()为空，待TextView渲染结束后重新获取Layout对象。
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            int ellipsisCount = textView.getLayout().getEllipsisCount(textView.getLineCount() - 1);
                            //是否超出范围:如果行数大于3或者而且ellipsisCount>0超出范围，会显示省略号。
                            if (model.hasEllipsis == null) {
                                model.hasEllipsis = !(textView.getLineCount() <= LINES && ellipsisCount == 0);
                            }
                            //如果文字没有超出范围，则隐藏按钮。
                            holder.itemView.findViewById(R.id.btn).setVisibility(model.hasEllipsis ? View.VISIBLE : View.GONE);
                            //文字是否全部展示。
                            model.isShowAll = ellipsisCount > 0;
                            setTextViewLines(textView, ((TextView) holder.itemView.findViewById(R.id.btn)), !model.hasEllipsis || model.isShowAll);
                        }
                    });
                } else {
                    holder.itemView.findViewById(R.id.btn).setVisibility(model.hasEllipsis ? View.VISIBLE : View.GONE);
                    setTextViewLines(textView, ((TextView) holder.itemView.findViewById(R.id.btn)), !model.hasEllipsis || model.isShowAll);
                }

                holder.itemView.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        model.isShowAll = !model.isShowAll;
                        notifyItemChanged(position);
                        //这样直接设置展示、收起也可以。
//                        setTextViewLines(textView, ((TextView) holder.itemView.findViewById(R.id.btn)), !model.hasEllipsis || model.isShowAll);
                    }
                });
            }

            @Override
            public int getItemCount() {
                return data.size();
            }
        });
    }

    private void setTextViewLines(TextView textView, TextView button, boolean isShowAll) {
        if (!isShowAll) {
            //展示全部，按钮设置为点击收起。
            textView.setMaxHeight(getResources().getDisplayMetrics().heightPixels);
            button.setText("收起");
        } else {
            //显示3行，按钮设置为点击显示全部。
            textView.setMaxLines(LINES);
            button.setText("显示全部");
        }
    }


    /**
     * 数据模型。
     */
    public static final class DataModel {
        /**
         * 是否是显示全部
         */
        private boolean isShowAll;
        /**
         * 文字是否超出范围
         */
        private Boolean hasEllipsis;
        private String text;

        public DataModel(String text) {
            this.text = text;
        }

        public DataModel(Boolean hasEllipsis, String text) {
            this.hasEllipsis = hasEllipsis;
            this.text = text;
        }


    }
}
