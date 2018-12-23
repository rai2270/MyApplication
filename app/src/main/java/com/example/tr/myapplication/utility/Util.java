package com.example.tr.myapplication.utility;


public class Util {

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static String capitalizeFirst(String text) {
        if (text != null && text.length() > 0) {
            return Character.toUpperCase(text.charAt(0)) + text.substring(1);
        } else {
            return text;
        }
    }


    public static boolean isEmpty(CharSequence charSequence) {
        return !(charSequence != null && charSequence.length() > 0);
    }

}
