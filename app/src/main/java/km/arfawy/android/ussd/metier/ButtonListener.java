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

public class ButtonListener implements View.OnClickListener {

    Context context;

    public ButtonListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_browse:
                if(PhoneMetier.permission(context, Manifest.permission.READ_CONTACTS)){
                    Intent i = new Intent(context, ContactsActivity.class);
                    context.startActivity(i);
                }
                break;

            case R.id.btn_param:
                Toast.makeText(context,"Param ... ", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_send:
                if(PhoneMetier.permission(context, Manifest.permission.CALL_PHONE)){

                    EditText editNum = (EditText)((Activity)context).findViewById(R.id.edit_num);
                    EditText editCode = (EditText)((Activity)context).findViewById(R.id.edit_code);
                    String myNumber;
                    if(!editNum.getText().toString().isEmpty()){
                        myNumber = editNum.getText().toString();
                        if(!PhoneMetier.isMorocco(myNumber)) Toast.makeText(context,R.string.invalid_number, Toast.LENGTH_SHORT).show();
                        else{
                            myNumber = PhoneMetier.normalize(myNumber);
                            String str = context.getResources().getString(R.string.treatment);
                            Toast.makeText(context, str + myNumber, Toast.LENGTH_SHORT).show();
                            String ussdCode = "*120*21*" + myNumber + Uri.encode("#");
                            ((Activity)context).startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + ussdCode)));
                        }
                    }
                    if(!editCode.getText().toString().isEmpty()){
                        myNumber = editCode.getText().toString();
                        if(myNumber.length()==16){
                            String ussdCode = "*120*20*" + myNumber + Uri.encode("#");
                            ((Activity)context).startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + ussdCode)));
                        }
                        else Toast.makeText(context, R.string.invalid_number, Toast.LENGTH_SHORT).show();

                    }
                    if (editNum.getText().toString().isEmpty() && editCode.getText().toString().isEmpty())
                        Toast.makeText(context, R.string.void_field, Toast.LENGTH_SHORT).show();

                }
                break;
        }

    }
}
