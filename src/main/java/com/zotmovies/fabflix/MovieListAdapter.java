package com.zotmovies.fabflix;
import com.zotmovies.fabflix.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by LuanNguyen on 5/21/2017.
 */

public class MovieListAdapter extends ArrayAdapter{
    private LayoutInflater layoutInflater;

    public MovieListAdapter (Context context, List<String> movieList){
        super(context, 0, movieList);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parrent){



        MovieListHolder holder = null;


        if (convertView == null){
            holder = new MovieListHolder();

            convertView = layoutInflater.inflate(R.layout.custom_movielist, null);
            holder.setTitle((TextView) convertView.findViewById(R.id.title));
            convertView.setTag(holder);
        }
        else
            holder = (MovieListHolder) convertView.getTag();


        return convertView;
    }
}
