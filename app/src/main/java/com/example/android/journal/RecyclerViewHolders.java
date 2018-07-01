package com.example.android.journal;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by katherine on 7/1/18.
 */
public class RecyclerViewHolders extends RecyclerView.ViewHolder {
    private static final String TAG = RecyclerViewHolders.class.getSimpleName();
    public ImageView entryIcon;
    public TextView entryTitle;
    public ImageView deleteIcon;
    private List<Entry> entryObject;

    public RecyclerViewHolders(final View itemView, final List<Entry> entryObject) {
        super(itemView);
        this.entryObject = entryObject;
        entryTitle = (TextView) itemView.findViewById(R.id.entry_title);
        entryIcon = (ImageView) itemView.findViewById(R.id.entry_icon);
        deleteIcon = (ImageView) itemView.findViewById(R.id.entry_delete);
        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Deleting diary entry", Toast.LENGTH_LONG).show();
                String entryTitle = entryObject.get(getAdapterPosition()).getEntry();
                Log.d(TAG, "Entry Title " + entryTitle);
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query applesQuery = ref.orderByChild("entry").equalTo(String.valueOf(entryTitle));
                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e(TAG, "onCancelled", databaseError.toException());
                    }
                });
            }
        });
    }
}
