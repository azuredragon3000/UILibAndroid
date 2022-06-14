package com.myapp.mylibrary;

import android.widget.EditText;

import java.util.ArrayList;

public class AnimationEditText {

    ArrayList<EditText> arrayList;
    public AnimationEditText(ArrayList<EditText> i_arrEdit){
        //arrayList = new ArrayList<>();
        arrayList = i_arrEdit;
        for(int i=0;i<arrayList.size();i++){
            arrayList.get(i).setTranslationX(800);
            arrayList.get(i).setAlpha(0f);
        }
    }

    public void start(){
        int number = 0;
        for(int i=0;i<arrayList.size();i++) {
            arrayList.get(i).animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300+number).start();
            number = number + 100;
        }
    }
}
