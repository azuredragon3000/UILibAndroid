package com.myapp.mylibrary.boitinhyeu;

import android.os.CountDownTimer;

public class AnimChangeTextPerTimeBoiVoChong extends AnimChangeTextPerTime {

    InterfaceAnimBoiVoChong interfaceAnimBoiVoChong;
    public AnimChangeTextPerTimeBoiVoChong(InterfaceAnimation animChangeTextPerTime
            , InterfaceAnimBoiVoChong animChangeTextPerTimeBoiVoChong) {
        super(animChangeTextPerTime);
        this.interfaceAnimBoiVoChong = animChangeTextPerTimeBoiVoChong;
    }

    public void startRepeatingTask(long maxCounter, long diff){
        mStatusChecker.run();
        new CountDownTimer(maxCounter, diff) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                stopRepeatingTask();
                // perform work
                interfaceAnimBoiVoChong.boivochongResult();
            }
        }.start();
    }
}
