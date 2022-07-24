package com.myapp.mylibrary.boitinhyeu;

import java.util.List;

public class ContentBoiPhuongDong {

    DatabaseBoiTinhYeuPD databaseBoiPhuongDong;
    public String getPD(String fulldate,DatabaseBoiTinhYeuPD databaseBoiPhuongDong
    ,List<ModelCungMang> cungmang,UserPD userNam, UserPD userNu) {
        this.databaseBoiPhuongDong = databaseBoiPhuongDong;
        String dtnam = fulldate.split(":")[0];
        int stdatenam = Integer.parseInt(dtnam.split("-")[0]);
        int stmonnam = Integer.parseInt(dtnam.split("-")[1]);
        int styearnam = Integer.parseInt(dtnam.split("-")[2]);
        String dtnu = fulldate.split(":")[1];
        int stdatenu = Integer.parseInt(dtnu.split("-")[0]);
        int stmonnu = Integer.parseInt(dtnu.split("-")[1]);
        int styearnu = Integer.parseInt(dtnu.split("-")[2]);
        userNam.NamAmlich = FunctionCommon.convertAmlichV2(stdatenam, stmonnam, styearnam);
        userNu.NamAmlich = FunctionCommon.convertAmlichV2(stdatenu, stmonnu, styearnu);
        String ret = "";
        userNam.getIndex(cungmang);
        userNu.getIndex(cungmang);

        String temp3 = databaseBoiPhuongDong.getContent((userNu.index) + String.valueOf(userNam.index));
        if (temp3 != null) {

            ret =   "năm sinh âm lịch của Nam là: " + userNam.NamAmlich + "\n"+
                    "Nam thuộc cung: " + userNam.cung + "\n"+
                    "năm sinh âm lịch của Nữ là: " + userNu.NamAmlich + "\n"+
                    "Nữ thuộc cung: " + userNu.cung + "\n"+ temp3+
                    "\n\n" +"kết quả trên dựa vào dử liệu đúc kết từ thời trước nên không nên tin tuyệt đối, \n cơ bản vẫn là do cách sống 2 bên ";
        }
        return ret;
    }

}
