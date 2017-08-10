package richie.ee.com.leaveamessage;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class NearByList extends AppCompatActivity {
private List<Message> mMessages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nearby_list);
        RecyclerView mRecyclerView;
        LinearLayoutManager mLayoutManager;
        RecyclerAdapter mRecyclerAdapter;

        mMessages = new ArrayList<>();
        if(getIntent().hasExtra("nearByList")) {
            Bundle bundle = getIntent().getExtras();
            mMessages = bundle.getParcelableArrayList("nearByList");
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerAdapter = new RecyclerAdapter(mMessages);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

//Can I make this Private?
    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        private List<Message> mDataset;

        //Constructor
        RecyclerAdapter(List<Message> myDataSet){
            mDataset = myDataSet;
        }


        class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            TextView mTextView;
            ViewHolder(TextView v) {
                super(v);
                mTextView = v;
            }
        }

        // Create new views (invoked by the layout manager)
        @Override
        public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            TextView v = (TextView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.nearby_item, parent, false);
            // set the view's size, margins, paddings and layout parameters
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            String messageString = mDataset.get(position).getMessage();
            holder.mTextView.setText(messageString);

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }


    }
}
