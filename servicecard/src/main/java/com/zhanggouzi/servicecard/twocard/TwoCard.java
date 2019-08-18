package com.zhanggouzi.servicecard.twocard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.zhanggouzi.servicecard.IColumn;
import com.zhanggouzi.servicecard.R;
import com.zhanggouzi.servicecard.simplecard.Card;
import com.zhanggouzi.servicecard.simplecard.CardBean;

/**
 * 目前只预置了一种效果，如果有多种根据columnStyle预置
 * 附加的设置，比如点击、滑动效果，可以在columnStyle对应配置文件中配置
 */

public class TwoCard extends LinearLayout  implements IColumn {


    private static final String TAG = "TwoCard";

    private Card cardA;
    private Card cardB;

    public TwoCard(Context context) {
       this(context, null);
    }

    public TwoCard(Context context, AttributeSet attrs) {
        this(context,attrs, 0);
    }

    public TwoCard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
        initView(context);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.TwoCard, defStyle, 0);


        a.recycle();
    }
    private void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.two_card, this);

        cardA = findViewById(R.id.card_a);
        cardB = findViewById(R.id.card_b);
    }


    /**
     * json会转换成ColumnBean
     * @param json 配置json
     */
    public void setData(String json){
        ColumnBean columnBean = new ColumnBean();
        if(!columnBean.fromJson(json))
        {
            Log.i(TAG, "failed ");
            return;
        }

        cardA.setData(columnBean.getA());
        cardB.setData(columnBean.getB());
    }

    @Override
    public String getName() {
        return "TwoCard";
    }
}
