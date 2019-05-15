package com.planet.treeplantations.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.planet.treeplantations.R;
import com.planet.treeplantations.models.Project_Responce_Model;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Admin on 7/7/2017.
 */

public class ProjectNameAdapter extends ArrayAdapter<Project_Responce_Model> {
    Context context;
    int textViewResourceId;
    ArrayList<Project_Responce_Model> items, tempItems, suggestions;
    /**
     * Custom Filter implementation for custom suggestions we provide.
     */
    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            String str = ((Project_Responce_Model) resultValue).toString();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (Project_Responce_Model people : tempItems) {
                   /* if (people.toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(people);
                    }*/

                    if (people.getProjectName().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(people);
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
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<Project_Responce_Model> filterList = (ArrayList<Project_Responce_Model>) results.values;
            try {
                if (results != null && results.count > 0) {
                    clear();
                    for (Project_Responce_Model people : filterList) {
                        add(people);
                        notifyDataSetChanged();
                    }
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }
    };

    public ProjectNameAdapter(Context context, int textViewResourceId, ArrayList<Project_Responce_Model> items) {
        super(context, textViewResourceId, items);
        this.context = context;
        this.textViewResourceId = textViewResourceId;
        this.items = items;
        tempItems = new ArrayList<Project_Responce_Model>(items); // this makes the difference.
        suggestions = new ArrayList<Project_Responce_Model>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_textview, parent, false);
        }
        try {
            Project_Responce_Model people = items.get(position);
            if (people != null) {
                TextView lblName = (TextView) view.findViewById(R.id.textView);
                if (lblName != null)
                    lblName.setText(people.getProjectName());
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return view;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }
}
