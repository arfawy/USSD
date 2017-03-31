package km.arfawy.android.ussd.models;

import android.net.Uri;

import km.arfawy.android.ussd.metier.PhoneMetier;

public class InwiMoroccoCountry extends Country {
    public InwiMoroccoCountry(){
        name = "inwi";
        starts = new String[]{"00212", "212"};
        minDigit = 10;
        recharge_request = "*120*21*" + "xxx" + Uri.encode("#");
        prepaid_recharge = "*120*20*" + "xxx" + Uri.encode("#");
    }

    public boolean isNationalNumber(String number){
        number = PhoneMetier.normalize(number);
        if(number.length() == getMinDigit()) return true;
        String [] starts = getStarts();
        for (int i = 0; i < starts.length; i++) {
            if(number.startsWith(getStarts()[i])
                    && number.length() == starts[i].length() + getMinDigit() - 1)
                return true;
        }
        return false;
    }

    public String simpleNumber(String number){
        number = PhoneMetier.normalize(number);
        if (!isNationalNumber(number)) return null;
        if(number.length() == getMinDigit()) return number;
        for (int i = 0; i < getStarts().length; i++) {
            if(number.startsWith(getStarts()[i]))
                return "0" + number.substring(getStarts()[i].length(), number.length());
        }
        return null;
    }
}
