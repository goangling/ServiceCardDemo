package com.zhanggouzi.servicecard.simplecard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhanggouzi.servicecard.ICard;
import com.zhanggouzi.servicecard.R;
import com.zhanggouzi.servicecard.bean.MappingRuleManager;
import com.zhanggouzi.servicecard.bean.ServiceCard;
import com.zhanggouzi.servicecard.glide.GlideWrapper;


/**
 * 目前只预置了一种效果，如果有多种，可以根据CardBean的style来预置
 * 附加的设置，比如点击、滑动效果，可以在CardBean的style对应配置文件中配置
 */
public class Card extends LinearLayout implements ICard {
    private static final String TAG = "card";
    private String cardTitle;
    private Drawable cardDrawable;

    private ImageView imageView;
    private TextView textView;

    public Card(Context context) {
        this(context, null);
    }

    public Card(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Card(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
        initView(context);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.Card, defStyle, 0);

        cardTitle = a.getString(R.styleable.Card_Title);


        if (a.hasValue(R.styleable.Card_Drawable)) {
            cardDrawable = a.getDrawable(R.styleable.Card_Drawable);
        }

        a.recycle();

    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.simple_card, this);

        imageView = findViewById(R.id.imageView);
        imageView.setImageDrawable(cardDrawable);

        textView = findViewById(R.id.textView);
        textView.setText(cardTitle);

    }


    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String exampleString) {
        this.cardTitle = exampleString;
        textView.setText(cardTitle);
    }

    public Drawable getCardDrawable() {
        return cardDrawable;
    }

    public void setCardDrawable(Drawable cardDrawable) {
        this.cardDrawable = cardDrawable;
        imageView.setImageDrawable(cardDrawable);
    }

    //方案1 预置显示数据，点击通过style.json配置
//    public void setData(CardBean bean) {
//        this.cardTitle = bean.getTitle();
//        textView.setText(cardTitle);
//
//        GlideWrapper.setImage(getContext(), bean.getIcon(), imageView);
//
//        MappingRuleManager mappingRuleManager = new MappingRuleManager(getContext());
//        if (!mappingRuleManager.init(getName(), bean.getStyle())) {
//            Log.e(TAG, "init failed");
//            return;
//        }
//        if (!mappingRuleManager.map(this,null)) {
//            Log.e(TAG, "failed to map");
//        }
//    }

    //方案2 显示数据、点击都通过style.json配置
    public void setData(String json) {
        ServiceCard serviceCard = new Gson().fromJson(json, ServiceCard.class);

        MappingRuleManager mappingRuleManager = new MappingRuleManager(getContext());
        if (!mappingRuleManager.init(getName(), serviceCard.getStyle())) {
            Log.e(TAG, "init failed");
            return;
        }
        if (!mappingRuleManager.map(this,serviceCard)) {
            Log.e(TAG, "failed to map");
        }
    }

    @Override
    public String getName() {
        return "simplecard";
    }
}
