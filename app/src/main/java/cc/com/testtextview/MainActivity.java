package cc.com.testtextview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    /**
     * 最多展示2行。
     */
    private static final int LINES = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = findViewById(R.id.tv);
        textView.setText(R.string.text);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取省略的字符数，0表示没和省略
                int ellipsisCount = textView.getLayout().getEllipsisCount(textView.getLineCount() - 1);
                textView.getLayout().getEllipsisCount(textView.getLineCount() - 1);
                //ellipsisCount>0说明没有显示全部，存在省略部分。
                if (ellipsisCount > 0) {
                    //展示全部，按钮设置为点击收起。
                    textView.setMaxHeight(getResources().getDisplayMetrics().heightPixels);
                    ((TextView) findViewById(R.id.btn)).setText("收起");
                } else {
                    //显示2行，按钮设置为点击显示全部。
                    ((TextView) findViewById(R.id.btn)).setText("显示全部");
                    textView.setMaxLines(LINES);
                }
            }
        });

        textView.post(new Runnable() {
            @Override
            public void run() {
                //获取省略的字符数，0表示没和省略
                int ellipsisCount = textView.getLayout().getEllipsisCount(textView.getLineCount() - 1);
                textView.getLayout().getEllipsisCount(textView.getLineCount() - 1);
            }
        });

        findViewById(R.id.btn_list_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestListActivity.class));
            }
        });
    }
}
