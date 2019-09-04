package org.lba.android.simple.trainer.activity.basic.ui.autocomplete.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import org.lba.android.simple.trainer.R;
import org.lba.android.simple.trainer.db.dao.EmployeeModelWithRoomDAO;
import org.lba.android.simple.trainer.db.model.EmployeeModelWithRoom;
import org.lba.android.simple.trainer.db.room.config.RoomDatabaseClient;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapterWithRoom extends ArrayAdapter {

    private List<EmployeeModelWithRoom> dataList;
    private Context mContext;
    private int itemLayout;
    private EmployeeModelWithRoomDAO employeeDao ;
    //= RoomDatabaseClient.getInstance(mContext).getAppDatabase().employeeDao();

    private EmployeeAdapterWithRoom.ListFilter listFilter = new EmployeeAdapterWithRoom.ListFilter();

    public EmployeeAdapterWithRoom(Context context, int resource, List<EmployeeModelWithRoom> storeDataLst) {
        super(context, resource, storeDataLst);
        employeeDao = RoomDatabaseClient.getInstance(context).getAppDatabase().employeeDao();
        dataList = storeDataLst;
        mContext = context;
        itemLayout = resource;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public EmployeeModelWithRoom getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(itemLayout, parent, false);
        }

        EmployeeModelWithRoom modelSelected = getItem(position);
        TextView strName = (TextView) view.findViewById(R.id.textView);
        strName.setText(modelSelected.getName());

        return view;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return listFilter;
    }

    public class ListFilter extends Filter {
        private Object lock = new Object();

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();

            if (prefix == null || prefix.length() == 0) {
                synchronized (lock) {
                    results.values = new ArrayList<String>();
                    results.count = 0;
                }
            } else {
                final String searchStrLowerCase = prefix.toString().toLowerCase();

                //Call to database to get matching records using room
                List<EmployeeModelWithRoom> matchValues =
                        employeeDao.getEmployeesFilterByName(searchStrLowerCase+"%");

                results.values = matchValues;
                results.count = matchValues.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.values != null) {
                dataList = (ArrayList<EmployeeModelWithRoom>)results.values;
            } else {
                dataList = null;
            }
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }

    }
}
