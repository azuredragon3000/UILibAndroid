package com.myapp.mylibrary.boitinhyeu;


public class ContentBoiPhuongTay {

    com.myapp.lovetest.boitinhyeu.data.DatabaseBoiTinhYeuCHD databaseCungHoangDao;
    public ContentBoiPhuongTay( com.myapp.lovetest.boitinhyeu.data.DatabaseBoiTinhYeuCHD databaseCungHoangDao){
        this.databaseCungHoangDao = databaseCungHoangDao;
    }

    public String getCHD(String fulldate){
        String dtnam = fulldate.split(":")[0];
        String dtnu = fulldate.split(":")[1];

        int datename = Integer.parseInt(dtnam.split("-")[0]);
        int datenu = Integer.parseInt(dtnu.split("-")[0]);
        int monthnam = Integer.parseInt(dtnam.split("-")[1]);
        int monthnu = Integer.parseInt(dtnu.split("-")[1]);

        String cungNam = cunghoangdao(convert(monthnam), datename);
        String cungNu = cunghoangdao(convert(monthnu), datenu);

        return databaseCungHoangDao.getContent(cungNam+"_"+cungNu);
    }

    private String cunghoangdao(String month, int date) {
        String sign = "";
        switch (month) {
            case "January":
                if (date < 20)
                    sign = "Capricorn";
                else
                    sign = "Aquarius";
                break;
            case "February":
                if (date < 19)
                    sign = "Aquarius";
                else
                    sign = "Pisces";
                break;
            case "March":
                if (date < 21)
                    sign = "Pisces";
                else
                    sign = "Aries";
                break;
            case "April":
                if (date < 20)
                    sign = "Aries";
                else
                    sign = "Taurus";
                break;
            case "May":
                if (date < 21)
                    sign = "Taurus";
                else
                    sign = "Gemini";
                break;
            case "June":
                if (date < 21)
                    sign = "Gemini";
                else
                    sign = "Cancer";
                break;
            case "July":
                if (date < 23)
                    sign = "Cancer";
                else
                    sign = "Leo";
                break;
            case "August":
                if (date < 23)
                    sign = "Leo";
                else
                    sign = "Virgo";
                break;
            case "September":
                if (date < 23)
                    sign = "Virgo";
                else
                    sign = "Libra";
                break;
            case "October":
                if (date < 23)
                    sign = "Libra";
                else
                    sign = "Scorpio";
                break;
            case "November":
                if (date < 22)
                    sign = "Scorpio";
                else
                    sign = "Sagittarius";
                break;
            case "December":
                if (date < 22)
                    sign = "Sagittarius";
                else
                    sign = "Capricorn";
                break;
        }
        return sign;
    }

    private String convert(int monthnam) {
        switch (monthnam) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "somethingwrong";
        }
    }
}
