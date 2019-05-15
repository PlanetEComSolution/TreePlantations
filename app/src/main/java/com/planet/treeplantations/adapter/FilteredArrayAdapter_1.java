package com.planet.treeplantations.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.planet.treeplantations.R;
import com.planet.treeplantations.models.Project_Code_Responce_Model;
import com.planet.treeplantations.models.Project_Responce_Model;

import java.util.ArrayList;
import java.util.List;

public class FilteredArrayAdapter_1 extends ArrayAdapter<Project_Code_Responce_Model> {

    private List<Project_Code_Responce_Model> objects;
    private Context context;
    private Filter filter;

    public FilteredArrayAdapter_1(Context context, int resourceId, List<Project_Code_Responce_Model> objects) {
        super(context, resourceId, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Project_Code_Responce_Model getItem(int position) {
        return objects.get(position);
    }

  /*  @Override
    public long getItemId(int position) {
        return objects.get(position).getId();
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // TODO: inflate your view HERE ...
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_textview, parent, false);
        }
        try {
            Project_Code_Responce_Model people = objects.get(position);
            if (people != null) {
                TextView lblName = (TextView) view.findViewById(R.id.textView);
                if (lblName != null)
                    lblName.setText(people.getProjectCodeName());
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return view;
    }

    @Override
    public Filter getFilter() {
        if (filter == null)
            filter = new AppFilter<Project_Code_Responce_Model>(objects);
        return filter;
    }

    /**
     * Class for filtering in Arraylist listview. Objects need a valid
     * 'toString()' method.
     *
     * @author Tobias Schürg inspired by Alxandr
     *         (http://stackoverflow.com/a/2726348/570168)
     *
     */
    private class AppFilter<T> extends Filter {

        private ArrayList<T> sourceObjects;

        public AppFilter(List<T> objects) {
            sourceObjects = new ArrayList<T>();
            synchronized (this) {
                sourceObjects.addAll(objects);
            }
        }

        @Override
        protected FilterResults performFiltering(CharSequence chars) {
            String filterSeq = chars.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if (filterSeq != null && filterSeq.length() > 0) {
                ArrayList<T> filter = new ArrayList<T>();

                for (T object : sourceObjects) {
                    // the filtering itself:
                    if (object.toString().toLowerCase().contains(filterSeq))
                        filter.add(object);
                }
                result.count = filter.size();
                result.values = filter;
            } else {
                // add all objects
                synchronized (this) {
                    result.values = sourceObjects;
                    result.count = sourceObjects.size();
                }
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            // NOTE: this function is *always* called from the UI thread.
            ArrayList<T> filtered = (ArrayList<T>) results.values;
            notifyDataSetChanged();
            clear();
            if(filtered!=null && filtered.size()>0) {
                for (int i = 0, l = filtered.size(); i < l; i++)
                    add((Project_Code_Responce_Model) filtered.get(i));
            }
            notifyDataSetInvalidated();
        }
    }

}