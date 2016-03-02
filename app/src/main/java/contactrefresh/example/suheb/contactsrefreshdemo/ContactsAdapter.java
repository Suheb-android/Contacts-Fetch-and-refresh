package contactrefresh.example.suheb.contactsrefreshdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suheb on 2/3/16.
 */
public class ContactsAdapter extends BaseAdapter {
    List<ContactsModelClass> al = new ArrayList<>();
    Context context;
    ViewHolder holder;

    public ContactsAdapter(List<ContactsModelClass> contacts, Context context) {
        this.context = context;
        this.al = contacts;
    }

    @Override
    public int getCount() {
        return al.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
//            holder.ivCurrency = (ImageView) convertView.findViewById(R.id.ivCurrency);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.number = (TextView) convertView.findViewById(R.id.number);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.name.setText(al.get(position).getName());
        holder.number.setText(al.get(position).getNumber());

        return convertView;
    }

    public class ViewHolder {
        TextView name;
        TextView number;
    }
}
