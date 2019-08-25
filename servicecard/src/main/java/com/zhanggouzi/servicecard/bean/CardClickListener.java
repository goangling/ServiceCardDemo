package com.zhanggouzi.servicecard.bean;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.zhanggouzi.servicecard.utils.UIUtil;

public class CardClickListener implements View.OnClickListener {
    private MappingRule rule;
    private Context context;

    public CardClickListener(Context context,MappingRule rule) {
        this.rule = rule;
        this.context = context;
    }

    @Override
    public void onClick(View v) {

//        Toast.makeText(v.getContext(), "destination is " + rule.getDestination(), Toast.LENGTH_SHORT).show();

        switch (rule.getDestinationConfigure().getType()) {
            case DestinationConfigure.ACTIVITY_TYPE:
                startActivity();
                break;
            case DestinationConfigure.ACTION_TYPE:
                startActivityByAction();
                break;
            default:
                break;
        }
    }

    private void startActivityByAction() {
        Intent intent = new Intent(rule.getDestinationConfigure().getUrl());
        setIntent(intent);
        UIUtil.startActivity(context, intent);

    }

    private void startActivity() {
        Intent intent = new Intent();
        intent.setClassName(context, rule.getDestinationConfigure().getUrl());
        setIntent(intent);
        UIUtil.startActivity(context, intent);
    }
    private void setIntent(Intent intent){
        ActionArgs args = rule.getArgs();
        if (args != null) {
            if (!TextUtils.isEmpty(args.getUri())) {
                intent.setData(Uri.parse(args.getUri()));
            }
            if (args.getFlags() != 0) {
                intent.setFlags(args.getFlags());
            }

            if (!TextUtils.isEmpty(args.getPackageName())
                    || !TextUtils.isEmpty(args.getClassName())) {
                intent.setClassName(args.getPackageName(), args.getClassName());
            }
           for(ActionArgs.Extra extra : args.getExtras()){
               intent.putExtra(extra.getKey(),extra.getValue());
           }
        }
    }
}

