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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListViewCookingActivity extends Activity {
DatabaseReference dbReference;
ListView listView;
ArrayList<InformationObject> cookingObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        cookingObject = new ArrayList<>();
        dbReference = FirebaseDatabase.getInstance().getReference().child("Categories").child("Cooking");
        listView = (ListView)findViewById(R.id.listView);
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot data:dataSnapshot.getChildren())
                {
                    InformationObject info = data.getValue(InformationObject.class);
                    cookingObject.add(info);

                }
                BasicAdapter adapter = new BasicAdapter();
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getBaseContext(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }


    class BasicAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return cookingObject.size();
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
            view = getLayoutInflater().inflate(R.layout.custom_list,null);
            TextView nameOfItem = (TextView)view.findViewById(R.id.ItemNameCustom);
            TextView Quantity = (TextView)view.findViewById(R.id.QuantityOfItem);
            TextView DateToBeDelivered = (TextView)view.findViewById(R.id.DateToBeDelivered);
            TextView phoneNumber=(TextView)view.findViewById(R.id.PhoneNumber);

            nameOfItem.setText(""+cookingObject.get(i).getItemName());
            Quantity.setText(""+cookingObject.get(i).getQuantity());
            DateToBeDelivered.setText(""+cookingObject.get(i).getDate());
            phoneNumber.setText(cookingObject.get(i).getPhoneNumber());

            return view;
        }
    }
}
