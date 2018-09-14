package skillsandservices.main.com.skillsandservices;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListViewPaintingActivity extends Activity {
    DatabaseReference dbReference;
    ListView  listView;
    ArrayList<PaintObject> paintingObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        paintingObject = new ArrayList<>();
        dbReference = FirebaseDatabase.getInstance().getReference().child("Categories").child("Painting");
        listView = (ListView)findViewById(R.id.listView);
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot data:dataSnapshot.getChildren())
                {
                   PaintObject info = data.getValue(PaintObject.class);
                    paintingObject.add(info);
                }
                BasicAdapter adapter = new BasicAdapter();
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getBaseContext(),databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }


    class BasicAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return paintingObject.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.activity_list_view_painting,null);
            TextView typename = (TextView)view.findViewById(R.id.typename);
            TextView nooforders = (TextView)view.findViewById(R.id.nooforders);
            TextView DateToBeDelivered = (TextView)view.findViewById(R.id.DateToBeDelivered);
            TextView phoneNumber=(TextView)view.findViewById(R.id.PhoneNumber);

            typename.setText(paintingObject.get(i).getTypeOfPainting());
            nooforders.setText(paintingObject.get(i).getNoOfOrders());
            DateToBeDelivered.setText(paintingObject.get(i).getDateToBeDelivered());
            phoneNumber.setText(paintingObject.get(i).getPhone());

            return view;
        }
    }
}


