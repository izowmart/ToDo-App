package com.example.artitectureexample.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artitectureexample.R;
import com.example.artitectureexample.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {
//    private List<Note> notes = new ArrayList<>();
    private OnItemClickListener listener;

    private Context context;

    public NoteAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription())&&
                    oldItem.getPriority() == (newItem.getPriority());
        }
    };


    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_note_item, parent, false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNotes = getItem(position);
        holder.title.setText(currentNotes.getTitle());
        holder.description.setText(currentNotes.getDescription());
        holder.priority.setText(String.valueOf(currentNotes.getPriority()));
    }
// Our List adapter will take care of this method

//    @Override
//    public int getItemCount() {
//        return notes.size();
//    }
//
//    public void setNotes(List<Note> notes) {
//        this.notes = notes;
//        notifyDataSetChanged();
//    }

    public Note getNoteAt(int position) {
        return getItem(position);
    }

    public class NoteHolder extends RecyclerView.ViewHolder {
        private TextView title, description, priority;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_title);
            description = itemView.findViewById(R.id.text_view_description);
            priority = itemView.findViewById(R.id.text_view_priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
//                    prevents clicking an item that has already been deleted.
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
