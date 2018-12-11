package ffadilaputra.org.bottom_toolbar.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.location.places.Place;

import java.util.List;

import ffadilaputra.org.bottom_toolbar.R;

public class PlacesRecyclerViewAdapter extends RecyclerView.Adapter<PlacesRecyclerViewAdapter.ViewHolder> {

	private List<Place> placesList;
	private Context context;

	public PlacesRecyclerViewAdapter(List<Place> placesList, Context context) {
		this.placesList = placesList;
		this.context = context;
	}

	@NonNull
	@Override
	public PlacesRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.places_item,viewGroup,false);
		PlacesRecyclerViewAdapter.ViewHolder viewHolder =
				new PlacesRecyclerViewAdapter.ViewHolder(view);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		final int itemPos = position;
		final Place place = placesList.get(position);
		holder.name.setText(place.getName());
		holder.address.setText(place.getAddress());
		holder.phone.setText(place.getPhoneNumber());
		if(place.getWebsiteUri() != null){
			holder.website.setText(place.getWebsiteUri().toString());
		}

		if(place.getRating() > -1){
			holder.ratingBar.setNumStars((int)place.getRating());
		}else{
			holder.ratingBar.setVisibility(View.GONE);
		}

		holder.viewOnMap.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//showOnMap(place);
			}
		});
	}


	@Override
	public int getItemCount() {
		return 0;
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		public TextView name;
		public TextView address;
		public TextView phone;
		public TextView website;
		public RatingBar ratingBar;

		public Button viewOnMap;

		public ViewHolder(View view) {

			super(view);

			name = view.findViewById(R.id.name);
			address = view.findViewById(R.id.address);
			phone = view.findViewById(R.id.phone);
			website = view.findViewById(R.id.website);
			ratingBar = view.findViewById(R.id.rating);

			viewOnMap = view.findViewById(R.id.view_map_b);
		}
	}
}