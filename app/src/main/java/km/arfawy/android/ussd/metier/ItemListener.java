package km.arfawy.android.ussd.metier;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import km.arfawy.android.ussd.activity.MainActivity;
import km.arfawy.android.ussd.activity.NumbersActivity;
import km.arfawy.android.ussd.models.Contact;


public class ItemListener implements View.OnClickListener{
    private Object obj;
    private Context context;

    public ItemListener(Context context, Object obj){
        this.obj = obj;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (obj.getClass().getSimpleName()){

            case "Contact":
                Contact ct = (Contact)obj;
                i = new Intent(context, NumbersActivity.class);
                i.putExtra("contact", ct);
                context.startActivity(i);
                break;
            case "String":
                String num = (String)obj;
                i = new Intent(context, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("number", num);
                context.startActivity(i);
                break;
        }
    }
}
