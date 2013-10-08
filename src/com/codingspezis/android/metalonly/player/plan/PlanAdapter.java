package com.codingspezis.android.metalonly.player.plan;

import java.util.*;

import android.content.*;
import android.view.*;
import android.widget.*;

import com.codingspezis.android.metalonly.player.*;
import com.codingspezis.android.metalonly.player.plan.views.*;
import com.codingspezis.android.metalonly.player.utils.*;

public class PlanAdapter extends BaseAdapter {

	private final ArrayList<Item> data;
	private static LayoutInflater inflater = null;
	private final ImageLoader imageLoader;
	private Context context;

	public PlanAdapter(Context context, ArrayList<Item> data) {
		this.context = context;
		
		this.data = data;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(context.getApplicationContext());
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;

		final Item item = data.get(position);
		if (item != null) {
			if (item.isSection()) {
				v = inflateSectionView(item);
			} else {
				v = inflateEntryItemView(position);
			}
		}
		return v;

	}

	private View inflateEntryItemView(int position) {
		PlanEntryView view = PlanEntryView_.build(context, null);
		PlanData tmpData = data.get(position).getPlanData();
		view.bind(tmpData);
		return view;
	}

	private View inflateSectionView(final Item item) {
		View v = inflate(R.drawable.plan_section);
		v.setOnClickListener(null);
		v.setOnLongClickListener(null);
		v.setLongClickable(false);
		final TextView sectionView = (TextView) v.findViewById(R.id.list_item_section_text);

		SectionItem si = (SectionItem) item;
		sectionView.setText(si.getTitle());
		return v;
	}

	private View inflate(int layout) {
		return inflater.inflate(layout, null);
	}

}