package com.myapp.mylibrary.boitinhyeu;

import android.annotation.SuppressLint;
import android.os.Handler;


public class AnimChangeTextPerTime {
    private int mInterval = 100;
    private Handler mHandler;
    private InterfaceAnimation animChangeTextPerTime;

    public AnimChangeTextPerTime(InterfaceAnimation animChangeTextPerTime){
        this.animChangeTextPerTime = animChangeTextPerTime;
        mHandler = new Handler();
    }
    public Runnable mStatusChecker = new Runnable() {
        @SuppressLint("SetTextI18n")
        @Override
        public void run() {
            try{
                animChangeTextPerTime.doWork();
            }finally {
                mHandler.postDelayed(mStatusChecker,mInterval);
            }
        }
    };

    public void startRepeatingTask(){
        mStatusChecker.run();
    }
    public void stopRepeatingTask(){
        mHandler.removeCallbacks(mStatusChecker);
    }
}
