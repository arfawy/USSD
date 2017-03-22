package km.arfawy.android.ussd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import km.arfawy.android.ussd.metier.ItemListener;
import km.arfawy.android.ussd.R;


public class NumberAdapter extends ArrayAdapter<String> {
    private Context context;
    private ArrayList<String> numbers;
    TextView text_number;
    RelativeLayout layout;

    public NumberAdapter(Context context, ArrayList<String> numbers) {
        super(context, R.layout.model_ct, numbers);
        this.context = context;
        this.numbers = numbers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String number = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.model_ct, parent, false);
        text_number = (TextView) convertView.findViewById(R.id.lv_name);
        layout = (RelativeLayout) convertView.findViewById(R.id.model_ct);
        text_number.setText(number);
        layout.setOnClickListener(new ItemListener(context,number));

        return convertView;
    }


}
