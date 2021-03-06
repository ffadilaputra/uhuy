package ffadilaputra.org.bottom_toolbar.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ffadilaputra.org.bottom_toolbar.R;
import ffadilaputra.org.bottom_toolbar.model.History;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {

    private Context context;
    private List<History> historyList;

    public HistoryAdapter(Context context , List<History> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final History history = historyList.get(position);
        holder.title.setText(history.getTitle());
        holder.description.setText(history.getDescription());
        holder.year.setText(history.getYear());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),history.getId().toString(),Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view.getContext(),getClass());
                i.putExtra("id", history.getId());
                //view.getContext().startActivity(get);
                final Long id = i.getLongExtra("id",0L);
                History history = History.findById(History.class, id);
                history.delete();
            }
        });

    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,year,description;
        public Button delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            year = (TextView) itemView.findViewById(R.id.year);
            delete = (Button) itemView.findViewById(R.id.del);
        }
    }
}
