package com.myapp.mylibrary.button;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomButton1 extends androidx.appcompat.widget.AppCompatButton {


    public static void customView(View v) {
        GradientDrawable shape = new GradientDrawable();

        /* set background for object ( shape and color ) */
        int color1 = Color.parseColor("#AA66CC");
        shape.setColor(color1);
        shape.setCornerRadius(20);

        v.setBackground(shape);
    }

    /* contructor object (with non xml file) */
    public CustomButton1(@NonNull Context context) {
        super(context);
        customView(this);
    }

    /* contructor object (with xml file ) */
    public CustomButton1(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        customView(this);
    }

    /* contructor object */
    public CustomButton1(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        customView(this);
    }
}
