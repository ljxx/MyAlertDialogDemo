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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alertviewdemo.www.R;
/**
 * ============================================================
 *
 * 作 者 : 杨理想
 *
 * 创建日期 ：2016/11/3 15:33
 * 
 * 描 述 ：模Ios ,自定义弹框
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
public class MyDialogView extends Dialog {

    private TextView mUPwdOk,mUPwdCancel,mTitle,mShowCenter; //确定，取消，标题title，消息
    private LinearLayout mTitleLayout; //title布局,是否显示
    private int recLen = 0;
    private View mView,mOkOrCancelLine;
    private Context mContext;

    public MyDialogView(Context context) {
        super(context, R.style.mydialog);
        this.mContext = context;
        this.mView = LayoutInflater.from(getContext()).inflate(R.layout.msg_ok_cancel, null);  //通过LayoutInflater获取布局
        //初始化view控件
        initUi();
        setContentView(mView);
        //设置弹框宽度
        initAlertDialogWidth();
    }

    /**
     * 初始化view控件
     */
    private void initUi(){
        mShowCenter = (TextView) mView.findViewById(R.id.mShowCenter);
        mTitleLayout = (LinearLayout) mView.findViewById(R.id.mTitleLayout);
        mOkOrCancelLine = mView.findViewById(R.id.mOkOrCancelLine);
        mTitle = (TextView) mView.findViewById(R.id.mTitle);
        mUPwdOk = (TextView) mView.findViewById(R.id.mOk);
        mUPwdCancel = (TextView) mView.findViewById(R.id.mCancel);
    }

    /**
     * 是否显示标题title
     * @param isShow
     */
    public void isShowTitle(boolean isShow){
        if(isShow){
            mTitleLayout.setVisibility(View.VISIBLE);
        } else {
            mTitleLayout.setVisibility(View.GONE);
        }
    }

    /**
     * 是否显示取消
     */
    public void isNoShowCancle(boolean isShow) {
        if (!isShow) {
            mOkOrCancelLine.setVisibility(View.GONE);
            mUPwdCancel.setVisibility(View.GONE);
        }
    }

    /**
     * 设置是否能back
     * @param isNoBack
     */
    public void isNoBack(boolean isNoBack){
        setCancelable(!isNoBack);
    }

    /**
     * 设置弹框宽度或高度
     */
    private void initAlertDialogWidth() {
        //获取屏幕的分辨率（宽，高）
        DisplayMetrics dm = new DisplayMetrics();
        dm = mContext.getApplicationContext().getResources().getDisplayMetrics();
        //设置弹框的宽度和高度
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        layoutParams.width = (int) (dm.widthPixels * 0.75); //此处仅仅只设置弹框的宽度,也建议如此
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL);
        dialogWindow.setAttributes(layoutParams);
    }

    /**
     * 含有倒计时的确定或取消按钮
     */
    public void setCountDown (int mTime) {
        this.recLen = mTime;
        if(mTime > 0){
            mUPwdOk.setEnabled(false);
            mUPwdCancel.setEnabled(false);
            Message message = handler.obtainMessage(1);     // Message
            handler.sendMessageDelayed(message, 1000);
        }
    }

    /**
     * 设置标题内容
     * @param title 标题内容
     */
    public void setTitle(CharSequence title){
        mTitle.setText(title);
    }

    /**
     * 设置标题大小
     * @param mSize 标题大小
     */
    public void setTetileSize(float mSize){
        mTitle.setTextSize(mSize);
    }

    /**
     * 设置标题字体颜色
     * @param mColor 标题字体颜色
     */
    public void setTextColor(int mColor){
        mTitle.setTextColor(mColor);
    }

    /**
     * 设置文本信息
     * @param center 提示消息内容
     */
    public void setMessageCenter(CharSequence center){
        mShowCenter.setText(center);
    }

    /**
     * 设置提示消息文本大小
     * @param mSize 消息字体大小
     */
    public void setMessageSize(float mSize){
        mShowCenter.setTextSize(mSize);
    }

    /**
     * 设置消息字体颜色
     * @param mColor 字体颜色
     */
    public void setMessageColor(int mColor){
        mShowCenter.setTextColor(mColor);
    }

    /**
     * 设置确定事件
     * @param listener 确定事件
     */
    public void setOnOkListener(View.OnClickListener listener){
        mUPwdOk.setOnClickListener(listener);
    }

    /**
     * 设置取消事件
     * @param listener 取消事件
     */
    public void setOnCancelListener(View.OnClickListener listener){
        mUPwdCancel.setOnClickListener(listener);
    }

    /**
     * 设置确定或取消的字体大小
     * @param mSize
     */
    public void setOkOrCancleSize(float mSize){
        mUPwdOk.setTextSize(mSize);
        mUPwdCancel.setTextSize(mSize);
    }

    /**
     * 设置确定或取消字体的颜色
     * @param mCorlor
     */
    public void setOkOrCancleColor(int mCorlor){
        mUPwdOk.setTextColor(mCorlor);
        mUPwdCancel.setTextColor(mCorlor);
    }


    /**
     * 确定倒计时
     */
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
