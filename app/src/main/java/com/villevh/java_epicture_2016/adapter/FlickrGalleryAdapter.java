package com.villevh.java_epicture_2016.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.googlecode.flickrjandroid.photos.Photo;
import com.googlecode.flickrjandroid.photos.PhotoList;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.villevh.java_epicture_2016.MainActivity;
import com.villevh.java_epicture_2016.R;

public class FlickrGalleryAdapter extends BaseAdapter {
    private PhotoList photos;
    private MainActivity mActivity;
    private static LayoutInflater inflater = null;
    private RelativeLayout rView;
    private Context context;

    public FlickrGalleryAdapter(Context c, MainActivity mainActivity , PhotoList d, RelativeLayout rootView) {
        mActivity = mainActivity;
        photos = d;
        rView = rootView;
        context = c;
    }

    public int getCount() {
        return (photos.size());
    }

    public Object getItem(int position) {
        return (position);
    }

    public long getItemId(int position) {
        return (position);
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View imageV;

        imageV = inflater.inflate(R.layout.image_format, null);

        Log.d("size", String.valueOf(position));
        TextView title = (TextView) imageV.findViewById(R.id.grid_item_label_title);
        TextView perm = (TextView) imageV.findViewById(R.id.grid_item_label_perm);
        //TextView date = (TextView) imageV.findViewById(R.id.grid_item_label_date);

        ImageView image = (ImageView) imageV.findViewById(R.id.grid_item_image);
        GridView g = (GridView) rView.findViewById(R.id.flickrGallery);

        Photo photo = photos.get(position);
        if (photo.isPublicFlag()) {
            perm.setTextColor(Color.rgb(00,80,00));
            perm.setText("Public");
        }
        else {
            perm.setText("Private");
            perm.setTextColor(Color.RED);
        }
        /*if (photo.getDateAdded() == null) {
            date.setText("Unknown");
        }
        else {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date d = photo.getDateAdded();
            String reportDate = df.format(d);
            date.setText(reportDate);
        }*/
        if (photo.getTitle() == "") {
            title.setTextColor(Color.RED);
            title.setText("No Name");
        }
        else {
            title.setText(photo.getTitle());
        }
        UrlImageViewHelper.setUrlDrawable(image, photo.getSmallSquareUrl());
        Log.d("url: ", photo.getMediumUrl());

        return (imageV);
    }
}