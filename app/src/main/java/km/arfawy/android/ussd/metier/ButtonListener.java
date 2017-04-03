package km.arfawy.android.ussd.metier;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import km.arfawy.android.ussd.R;
import km.arfawy.android.ussd.activity.ContactsActivity;
import km.arfawy.android.ussd.models.Country;

public class ButtonListener implements View.OnClickListener {

    Context context;
    PhoneMetier metier = new PhoneMetier();
    Country country = metier.getCountry();

    public ButtonListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_browse:
                if(metier.permission(context, Manifest.permission.READ_CONTACTS)){
                    Intent i = new Intent(context, ContactsActivity.class);
                    context.startActivity(i);
                }
                break;

            case R.id.btn_param:
                Toast.makeText(context,"Param ... ", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_send:
                if(metier.permission(context, Manifest.permission.CALL_PHONE)){

                    EditText editNum = (EditText)((Activity)context).findViewById(R.id.edit_num);
                    EditText editCode = (EditText)((Activity)context).findViewById(R.id.edit_code);
                    String ussdCode = null;
                    //Recharge-Moi
                    if(!editNum.getText().toString().isEmpty()){
                        String number = editNum.getText().toString();
                        if(!country.isNationalNumber(number)) Toast.makeText(context,R.string.invalid_number, Toast.LENGTH_SHORT).show();
                        else{
                            number = metier.normalize(number);
                            String str = context.getResources().getString(R.string.treatment);
                            Toast.makeText(context, str + number, Toast.LENGTH_SHORT).show();
                            ussdCode = country.getRecharge_request().replace("xxx", number);
                        }
                    }
                    //Recharge
                    if(!editCode.getText().toString().isEmpty()){
                        String code = editCode.getText().toString();
                        if(code.length()==16){
                            ussdCode = country.getPrepaid_recharge().replace("xxx", code);
                        }
                        else Toast.makeText(context, R.string.invalid_number, Toast.LENGTH_SHORT).show();
                    }
                    if (editNum.getText().toString().isEmpty() && editCode.getText().toString().isEmpty())
                        Toast.makeText(context, R.string.void_field, Toast.LENGTH_SHORT).show();
                    //Execution USSD
                    if(ussdCode != null) context.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + ussdCode)));
                }
                break;
        }

    }
}
