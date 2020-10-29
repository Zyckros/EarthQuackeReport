package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarquakeAdapter extends ArrayAdapter<Earthquake> {


    String primaryLocation;
    String locationOffset;

    public EarquakeAdapter(@NonNull Context context, int resource, @NonNull List<Earthquake> objects) {
        super(context, resource, objects);
    }


    public View getView(int position, View convertView, ViewGroup parent){
        View listItView = convertView;

        if(listItView == null){
            listItView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,parent,false);
        }

        Earthquake currentEarthquake = getItem(position);

        TextView magnitudeView = (TextView)  listItView.findViewById(R.id.magnitude);
        assert currentEarthquake != null;

        Double magnitude = currentEarthquake.getmMagnitude();

        NumberFormat numberFormat= DecimalFormat.getInstance();

        String formatMagnitude = numberFormat.format("0.0");

        magnitudeView.setText(formatMagnitude);


        String originalLocation = currentEarthquake.getmLocation();

        if(originalLocation.contains(" of ")){

            String[] splitLocation = originalLocation.split("of");
            primaryLocation = splitLocation[0] + " of";
            locationOffset = splitLocation[1];

        }else{
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;

        }

        TextView locationView1 = (TextView)  listItView.findViewById(R.id.primary_location);
        locationView1.setText(locationOffset);

        TextView locationView2 = (TextView)  listItView.findViewById(R.id.location_offset);
        locationView2.setText(primaryLocation);



        Date dateObject = new Date(currentEarthquake.getmTimeInMilliseconds());
        String formattedDate = formatDate(dateObject);

        TextView dateView = (TextView)  listItView.findViewById(R.id.date);
        dateView.setText(formattedDate);

        String formattedTime = formatTime(dateObject);

        TextView timeView = listItView.findViewById(R.id.time);
        timeView.setText(formattedTime);


        return listItView;

    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
