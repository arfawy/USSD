package km.arfawy.android.ussd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import km.arfawy.android.ussd.R;
import km.arfawy.android.ussd.metier.ItemListener;
import km.arfawy.android.ussd.models.Contact;

public class ContactAdapter extends ArrayAdapter<Contact> {
    private ArrayList<Contact> cts;
    Context context;
    TextView name;
    RelativeLayout layout;

    public ContactAdapter(ArrayList<Contact> cts, Context context) {
        super(context, R.layout.model_ct, cts);
        this.cts = cts;
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Contact ct = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.model_ct, parent, false);
        name = (TextView) convertView.findViewById(R.id.lv_name);
        layout = (RelativeLayout) convertView.findViewById(R.id.model_ct);
        name.setText(ct.getNom());
        layout.setOnClickListener(new ItemListener(context,ct));
        return convertView;
    }
}
