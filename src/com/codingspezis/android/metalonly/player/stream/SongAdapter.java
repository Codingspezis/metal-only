package com.codingspezis.android.metalonly.player.stream;

import java.text.*;
import java.util.*;

import android.app.*;
import android.content.*;
import android.view.*;
import android.widget.*;

import com.codingspezis.android.metalonly.player.*;
import com.codingspezis.android.metalonly.player.favorites.*;
import com.codingspezis.android.metalonly.player.utils.*;

/**
 * 
 * adapter for displaying a 3 row list view
 * 
 */
public class SongAdapter extends BaseAdapter {

	// hash keys
	public static final String KEY_TITLE = "MO_HK_TITLE";
	public static final String KEY_INTERPRET = "MO_HK_INTERPRET";
	public static final String KEY_THUMB = "MO_HK_THUMB";
	public static final String KEY_DATE = "MO_HK_DATE";

	private final Activity activity;
	private final ArrayList<Song> data;
	private final LayoutInflater inflater;
	private final ImageLoader imageLoader;

	/**
	 * constructor
	 * 
	 * @param a
	 * @param d
	 *            data to display
	 */
	public SongAdapter(Activity a, ArrayList<Song> d) {
		activity = a;
		data = d;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(a.getApplicationContext());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = inflater.inflate(R.layout.song_hist, null);
		}

		Song song = data.get(position);

		final TextView txtTitle = (TextView) view.findViewById(R.id.txtTitle);
		final TextView txtArtist = (TextView) view.findViewById(R.id.txtArtist);
		final TextView txtTime = (TextView) view.findViewById(R.id.txtTime);
		final TextView txtDate = (TextView) view.findViewById(R.id.txtDate);

		txtTitle.setText(song.title);
		txtArtist.setText(song.interpret);
		try {
			final Date dateAsDate = new Date(song.date);
			String day = DateFormat.getDateInstance(DateFormat.SHORT,
					Locale.GERMAN).format(dateAsDate);
			String time = DateFormat.getTimeInstance(DateFormat.SHORT,
					Locale.GERMAN).format(dateAsDate);

			txtDate.setText(day);
			txtTime.setText(time);
		} catch (Exception e) {
			txtDate.setText("");
			txtTime.setText("");
		}

		ImageView image = (ImageView) view.findViewById(R.id.modImage);
		String thumb = song.getThumb();
		imageLoader.DisplayImage(thumb, image);

		return view;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int pos) {
		return data.get(pos);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}
}
