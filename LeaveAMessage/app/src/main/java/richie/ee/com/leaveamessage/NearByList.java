package richie.ee.com.leaveamessage;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;


public class NearByList extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nearby_list);
        RecyclerView mRecyclerView;
        LinearLayoutManager mLayoutManager;
        RecyclerAdapter mRecyclerAdapter;

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        String [] myTestData = new String[]{"Hello","There","Guys","Hope"," THIS WORKS!"};
        mRecyclerAdapter = new RecyclerAdapter(myTestData);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }


    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        private String[] mDataset;

        //Constructor
        public RecyclerAdapter(String[] myDataSet){
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
            holder.mTextView.setText(mDataset[position]);

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.length;
        }


    }
}
