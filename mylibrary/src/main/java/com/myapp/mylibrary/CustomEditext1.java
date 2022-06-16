package com.myapp.mylibrary;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomEditext1 extends androidx.appcompat.widget.AppCompatEditText {

    public static void customView(View v) {
        GradientDrawable shape = new GradientDrawable();
        //shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(20);
        //shape.setCornerRadii(new float[] { 8, 8, 8, 8, 0, 0, 0, 0 });
        //shape.setColor(backgroundColor);
        int color1 = Color.parseColor("#D3D3D3");
        shape.setStroke(3, color1);
        v.setBackground(shape);
    }

    public CustomEditext1(@NonNull Context context) {
        super(context);
        this.setSingleLine(true);
        this.setImeOptions(EditorInfo.IME_ACTION_DONE);
        customView(this);
    }

    public CustomEditext1(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setSingleLine(true);
        this.setImeOptions(EditorInfo.IME_ACTION_DONE);
        customView(this);
    }

    public CustomEditext1(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setSingleLine(true);
        this.setImeOptions(EditorInfo.IME_ACTION_DONE);
        customView(this);
    }
}
