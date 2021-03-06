package km.arfawy.android.ussd.metier;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import km.arfawy.android.ussd.models.Contact;
import km.arfawy.android.ussd.models.Country;
import km.arfawy.android.ussd.models.InwiMoroccoCountry;

public class PhoneMetier {
    protected static Country country = new InwiMoroccoCountry();
    private final static int MY_PERMISSIONS_REQUEST = 10;

    public static String normalize(String number){

        char[] n = number.toCharArray();
        char[] res = new char[number.length()];
        int c = 0;
        for (int i = 0; i < n.length; i++) {
            if(isNumeric(n[i])){
                res[c] = n[i];
                c++;
            }
        }
        String str = String.valueOf(res);
        str =  str.substring(0, c);

        return str;
    }

    private static boolean isNumeric(char c) {
        char[] numeric = {'0','1','2','3','4','5','6','7','8','9'};
        for (int i = 0; i < numeric.length; i++) {
            if(c==numeric[i]) return true;
        }
        return false;
    }

    public static ArrayList<Contact> getContacts(Context context){
        ArrayList<Contact> cts = new ArrayList<>();
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null,null, null);

        while(cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String lastname = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHONETIC_NAME));
            Contact ct = new Contact(Integer.parseInt(id),name,lastname);
            Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null
            );
            while(phoneCursor.moveToNext()){
                String number = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                //number = PhoneMetier.simpleNumber(number);
                number = country.simpleNumber(number);
                if(number!= null && !ct.getNumbres().contains(number)) ct.add(number);
            }
            if(ct.getNumbres().size()!=0) cts.add(ct);
            phoneCursor.close();
        }

        cursor.close();
        Collections.sort(cts, new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                return o1.getNom().compareTo(o2.getNom());
            }
        });
        return cts;
    }
    public static boolean permission(Context context, String PERMISSION_TYPE){
        if(ActivityCompat.checkSelfPermission(context, PERMISSION_TYPE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions((Activity)context, new String[]{PERMISSION_TYPE},MY_PERMISSIONS_REQUEST);
        }
        else {
            return true;
        }
        return false;
    }


}
