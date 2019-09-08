package org.lba.android.simple.trainer.activity.basic.ui.autocomplete.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.lba.android.simple.trainer.R;
import org.lba.android.simple.trainer.activity.basic.ui.model.EmployeeUISampleModel;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends ArrayAdapter<EmployeeUISampleModel> {

    private Context context;
    private int resourceId;
    private List<EmployeeUISampleModel> items, tempItems, suggestions;

    public EmployeeAdapter(@NonNull Context context, int resourceId, ArrayList<EmployeeUISampleModel> items) {
        super(context, resourceId, items);
        this.items = items;
        this.context = context;
        this.resourceId = resourceId;
        tempItems = new ArrayList<>(items);
        suggestions = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                view = inflater.inflate(resourceId, parent, false);
            }
            EmployeeUISampleModel employee = getItem(position);
            TextView name = (TextView) view.findViewById(R.id.textView);

            name.setText(employee.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Nullable
    @Override
    public EmployeeUISampleModel getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return employeeFilter;
    }

    private Filter employeeFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            EmployeeUISampleModel employee = (EmployeeUISampleModel) resultValue;
            return employee.getName();
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            if (charSequence != null) {
                suggestions.clear();
                for (EmployeeUISampleModel employee: tempItems) {
                    if (employee.getName().toLowerCase().startsWith(charSequence.toString().toLowerCase())) {
                        suggestions.add(employee);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            ArrayList<EmployeeUISampleModel> tempValues = (ArrayList<EmployeeUISampleModel>) filterResults.values;
            if (filterResults != null && filterResults.count > 0) {
                clear();
                for (EmployeeUISampleModel employeeObj : tempValues) {
                    add(employeeObj);
                    notifyDataSetChanged();
                }
            } else {
                clear();
                notifyDataSetChanged();
            }
        }
    };
}
