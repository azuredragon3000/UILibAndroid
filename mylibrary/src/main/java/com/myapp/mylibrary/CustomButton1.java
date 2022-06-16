package com.myapp.mylibrary;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomButton1 extends androidx.appcompat.widget.AppCompatButton {

    public static void customView(View v) {
        GradientDrawable shape = new GradientDrawable();
        //shape.setShape(GradientDrawable.RECTANGLE);
        //shape.setCornerRadii(new float[] { 8, 8, 8, 8, 0, 0, 0, 0 });
        int color1 = Color.parseColor("#AA66CC");
        shape.setColor(color1);
        //shape.setStroke(3, borderColor);
        shape.setCornerRadius(20);
        v.setBackground(shape);
    }

    public CustomButton1(@NonNull Context context) {
        super(context);
        //int color1 = Color.parseColor("#AA66CC");
        //int color2 = Color.parseColor("#D3D3D3");
        customView(this);
    }

    public CustomButton1(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //int color1 = Color.parseColor("#AA66CC");
        //int color2 = Color.parseColor("#D3D3D3");
        customView(this);

    }

    public CustomButton1(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //int color1 = Color.parseColor("#AA66CC");
        //int color2 = Color.parseColor("#D3D3D3");
        customView(this);
    }
}
