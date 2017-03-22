package km.arfawy.android.ussd.metier;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import km.arfawy.android.ussd.R;


public class ItemSelectedListener implements AdapterView.OnItemSelectedListener {
    private Context context;
    //EditText

    public ItemSelectedListener(Context context) {
        this.context = context;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        EditText number = (EditText)((Activity)context).findViewById(R.id.edit_num);
        EditText code = (EditText)((Activity)context).findViewById(R.id.edit_code);
        ImageButton browse = (ImageButton)((Activity)context).findViewById(R.id.btn_browse);
        String s = parent.getItemAtPosition(position) + "";
        if(s.equals(context.getResources().getString(R.string.feedback1))) {
            browse.setVisibility(View.INVISIBLE);
            number.setVisibility(View.INVISIBLE);
            code.setVisibility(View.VISIBLE);
            number.getText().clear();
        }
        else{
            browse.setVisibility(View.VISIBLE);
            number.setVisibility(View.VISIBLE);
            code.setVisibility(View.INVISIBLE);
            code.getText().clear();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
