package com.alertviewdemo.www.myview;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.alertviewdemo.www.R;

public class MyDialogView extends Dialog {

    private TextView mUPwdOk,mUPwdCancel,mShowCenter;
    private int recLen = 0;

    public MyDialogView(Context context, int mTime) {
        super(context, R.style.mydialog);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.home_dialog_to_password, null);  //通过LayoutInflater获取布局
        mShowCenter = (TextView) view.findViewById(R.id.mShowCenter);
        mUPwdOk = (TextView) view.findViewById(R.id.mOk);
        mUPwdCancel = (TextView) view.findViewById(R.id.mCancel);
        mUPwdOk.setEnabled(false);
        mUPwdCancel.setEnabled(false);
        setContentView(view);

        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getApplicationContext().getResources().getDisplayMetrics();

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        layoutParams.width = (int) (dm.widthPixels * 0.75);
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL);
        dialogWindow.setAttributes(layoutParams);
        this.recLen = mTime;
        Message message = handler.obtainMessage(1);     // Message
        handler.sendMessageDelayed(message, 1000);
    }

    /**
     * 设置文本信息
     */
    public void setMessageCenter(CharSequence text){
        mShowCenter.setText(text);
    }

    /**
     * 设置确定事件
     * @param listener
     */
    public void setOnOkListener(View.OnClickListener listener){
        mUPwdOk.setOnClickListener(listener);
    }

    /**
     * 设置取消事件
     * @param listener
     */
    public void setOnCancelListener(View.OnClickListener listener){
        mUPwdCancel.setOnClickListener(listener);
    }


    final Handler handler = new Handler(){

        public void handleMessage(Message msg){         // handle message
            switch (msg.what) {
                case 1:
                    recLen--;
                    if(recLen > 0){
                        mUPwdOk.setEnabled(false);
                        mUPwdCancel.setEnabled(false);
                        mUPwdOk.setText("确定(" + recLen + ")");
                        Message message = handler.obtainMessage(1);
                        handler.sendMessageDelayed(message, 1000);      // send message
                    }else{
                        mUPwdOk.setEnabled(true);
                        mUPwdCancel.setEnabled(true);
                        mUPwdOk.setText("确定");
                    }
            }

            super.handleMessage(msg);
        }
    };
}
