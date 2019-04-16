package limited.pvt.global.stachi.glisteruser.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import limited.pvt.global.stachi.glisteruser.app.Model.Common;
import limited.pvt.global.stachi.glisteruser.app.Model.ProfileData;

public class GetAttendance extends AppCompatActivity
{
    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<ProfileData,ProfileViewHolder> adapter;
    FirebaseDatabase database;
    DatabaseReference requests;
    private String phone ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_attendance);

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Attendance");

        recyclerView = (RecyclerView) findViewById(R.id.retrieve_profile_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOrders(Common.currentUser.getPhone());
    }

    private void loadOrders(String name)
    {
        adapter = new FirebaseRecyclerAdapter < ProfileData, ProfileViewHolder >
                (
                        ProfileData.class,
                        R.layout.retrieve_profile_layout,
                        ProfileViewHolder.class,
                        requests.orderByChild("attendancePhone").equalTo(name)
                )
        {
            @Override
            protected void populateViewHolder(ProfileViewHolder viewHolder, ProfileData model, int position)
            {
                viewHolder.txtPhone.setText(model.getAttendanceDate());
                viewHolder.txtTime.setText(model.getAttendanceTime());
                viewHolder.txtStatus.setText(Common.convertCodeToStatus(model.getAttendanceStatus()));

                viewHolder.txtArea.setText(model.getAttendanceTargetArea());

                viewHolder.itemClickListener = new ItemClickListener()
                {
                    @Override
                    public void onClick(View view, int position)
                    {
                    }
                };
            }
        };
        recyclerView.setAdapter(adapter);
        requests.keepSynced(true);
    }
}
