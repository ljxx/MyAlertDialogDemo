package com.alertviewdemo.www;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alertviewdemo.www.myview.MyDialogView;

public class MainActivity extends Activity {

    private TextView mOkOrCancle01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();

        initListeren();
    }

    private void initUi() {
        mOkOrCancle01 = (TextView) findViewById(R.id.mOkOrCancle01);
    }

    private void initListeren() {
        mOkOrCancle01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MyDialogView myDialog=new MyDialogView(MainActivity.this,5);
                myDialog.setCancelable(false);
                myDialog.setMessageCenter(Html.fromHtml("<font color='#666666'>恭喜您，注册成为赵涌在线用户，<br/>默认初始密码为123456，</font><font color='#AA5066'>" +
                        "<br/>为了您的账户安全，请及时修改密码。<br/>" +
                        "</font><font color='#666666'>确认修改密码，请点击确定</font>"));
                myDialog.setOnOkListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"你点击确定了",Toast.LENGTH_SHORT).show();
                        if(myDialog != null){
                            myDialog.dismiss();
                        }
                    }
                });
                myDialog.setOnCancelListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"你点击取消了",Toast.LENGTH_SHORT).show();
                        if(myDialog != null){
                            myDialog.dismiss();
                        }
                    }
                });
                myDialog.show();
            }
        });
    }
}
