package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Utils {
    public static long getRandomNumber(long min, long max) {
        return (long) ((Math.random() * (max - min)) + min);
    }

    public static String getRandomPhoneNumber(){
        return "+7"+ Utils.getRandomNumber(1000000000, 9999999999L);
    }

    public static String getRandomString(int stringLegth) {
        String s = "абвгдежзиклмнопрстуфхцчшщэюя";
        StringBuffer str = new StringBuffer();

        for (int i = 0; i < stringLegth; i++) {
            str.append(s.charAt(new Random().nextInt(s.length())));
        }
        return str.toString();
    }

    public static String getRandomAddress(){
        String street = getRandomString(10);
        long house = getRandomNumber(1,100);
        return "ул " + street + " д. " + house;
    }

    public static String getNextDayString(){

        return LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    }
}



