package limited.pvt.global.stachi.glisteruser.app;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.entity.StringEntity;
import limited.pvt.global.stachi.glisteruser.app.Notification.APIService;
import limited.pvt.global.stachi.glisteruser.app.Notification.MyResponse;
import limited.pvt.global.stachi.glisteruser.app.Notification.Notification;
import limited.pvt.global.stachi.glisteruser.app.Notification.Sender;
import limited.pvt.global.stachi.glisteruser.app.Notification.Token;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import limited.pvt.global.stachi.glisteruser.app.Model.Common;

import limited.pvt.global.stachi.glisteruser.app.Model.Show_Chat_Conversation_Data_Items;


public class MessageList extends AppCompatActivity
{
    public RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef,myRef2;
    private FirebaseRecyclerAdapter<Show_Chat_Conversation_Data_Items, Chat_Conversation_ViewHolder> mFirebaseAdapter;
    public LinearLayoutManager mLinearLayoutManager;
    static String Sender_Name;
    ImageView  send_icon   ;
    ImageView attach_icon ;
    EditText message_area;
    private static final int GALLERY_INTENT = 2;
    private ProgressDialog mProgressDialog;
    ProgressBar progressBar;
    public static final int READ_EXTERNAL_STORAGE = 0,MULTIPLE_PERMISSIONS = 10;
    Uri mImageUri = Uri.EMPTY;

    private String pictureImagePath = "";
    final CharSequence[] options = { "Camera", "Gallery" } ;
    String[] permissions= new String[]{
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,};

    String dataTitle, dataMessage;

    APIService mService ;

    String USER_ID ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        mService = Common.getFCMService();

        USER_ID = Common.currentUser.getName();

        final String phoneNumber = getIntent().getStringExtra("name") ;

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference().child("Chat").child(USER_ID)
                .child("Admin Boss");
        myRef.keepSynced(true);

        myRef2 = FirebaseDatabase.getInstance().getReference().child("Chat").child("Admin Boss").child(USER_ID);
        myRef2.keepSynced(true);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(Html.fromHtml("<font color=#FFFFFF>" + getIntent().getStringExtra("name") + "</font>"));
        }
        Sender_Name = "Admin Boss";
        recyclerView = (RecyclerView)findViewById(R.id.fragment_chat_recycler_view);
  //      attach_icon = (ImageView)findViewById(R.id.attachButton);
        send_icon = (ImageView)findViewById(R.id.sendButton);

        message_area = (EditText)findViewById(R.id.messageArea);
        mProgressDialog = new ProgressDialog(this);
        progressBar = (ProgressBar)findViewById(R.id.progressBar3);

        mLinearLayoutManager = new LinearLayoutManager(MessageList.this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        mLinearLayoutManager.setStackFromEnd(true);

        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        send_icon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String messageText = message_area.getText().toString().trim();

                if(!messageText.equals(""))
                {
                    ArrayMap<String, String> map = new ArrayMap<>();
                    map.put("message", messageText);
                    map.put("sender", Common.currentUser.getName());
                    myRef.push().setValue(map);
                    myRef2.push().setValue(map);

                    String order_number = message_area.getText().toString().trim();

                    String order_name = String.valueOf(Common.currentUser.getName()) ;

                    sendNotificationOrder(order_number , order_name);

                    message_area.setText("");
                    recyclerView.postDelayed(new Runnable()
                    {
                        @Override public void run()
                        {
                            recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount()-1);

                        }
                    }, 500);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    callgalary();
                return;

            case MULTIPLE_PERMISSIONS:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    callCamera();
                }
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Show_Chat_Conversation_Data_Items, Chat_Conversation_ViewHolder>
                (
                        Show_Chat_Conversation_Data_Items.class,
                        R.layout.show_chat_conversation_single_item,
                        Chat_Conversation_ViewHolder.class,
                        myRef)
        {
            public void populateViewHolder(final Chat_Conversation_ViewHolder viewHolder, Show_Chat_Conversation_Data_Items model,
                                           final int position)
            {

                viewHolder.getSender(model.getSender());
                viewHolder.getMessage(model.getMessage());


                viewHolder.mView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(final View v) {

                        final DatabaseReference ref = mFirebaseAdapter.getRef(position);
                        ref.keepSynced(true);
                        ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String retrieve_image_url = dataSnapshot.child("message").getValue(String.class);
                                if(retrieve_image_url.startsWith("https"))
                                {
                                    Intent intent = (new Intent(MessageList.this,MessageList.class));
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });

            }
        };
        Log.d("LOGGED", "Set Layout : " );
        recyclerView.setAdapter(mFirebaseAdapter);

        myRef.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren())
                {

                    progressBar.setVisibility(ProgressBar.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                    recyclerView.postDelayed(new Runnable() {
                        @Override public void run()
                        {
                            recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount()-1);
                        }
                    }, 500);
                    recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                        @Override
                        public void onLayoutChange(View v,
                                                   int left, int top, int right, int bottom,
                                                   int oldLeft, int oldTop, int oldRight, int oldBottom) {
                            if (bottom < oldBottom) {
                                recyclerView.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount() - 1);
                                    }
                                }, 100);
                            }
                        }
                    });
                }
                else {

                    progressBar.setVisibility(ProgressBar.GONE);
                    recyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:

                this.onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static class Chat_Conversation_ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView message, sender;

        View mView;
        final LinearLayout.LayoutParams params,text_params;
        LinearLayout layout;

        public Chat_Conversation_ViewHolder(final View itemView)
        {
            super(itemView);

            mView = itemView;
            message = (TextView) mView.findViewById(R.id.fetch_chat_messgae);
            sender = (TextView) mView.findViewById(R.id.fetch_chat_sender);

            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            text_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layout = (LinearLayout) mView.findViewById(R.id.chat_linear_layout);
        }

        @SuppressLint("ResourceAsColor")
        private void getSender(String title)
        {
            if(title.equals(Common.currentUser.getName()))
            {
                text_params.setMargins(15,10,10,5);

                mView.setBackgroundResource(R.mipmap.editbackground);

                sender.setText("you");
            }
            else
            {

                sender.setGravity(Gravity.START);
                text_params.setMargins(60,10,10,5);

                sender.setTextColor(R.color.colorAccent);
                mView.setBackgroundResource(R.mipmap.loginbutton);

                sender.setText(Sender_Name);
            }
        }

        private void getMessage(String title)
        {
            if(!title.startsWith("https"))
            {

                if(!sender.getText().equals(Sender_Name))
                {
                    text_params.setMargins(15,10,22,15);
                }
                else
                {
                    text_params.setMargins(165,10,22,15);
                }

                message.setLayoutParams(text_params);
                message.setText(title);
                message.setTextColor(Color.parseColor("#000000"));
                message.setVisibility(View.VISIBLE);
            }
            else
            {
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("LOGGED", " requestCode : " + requestCode+" resultCode : " + resultCode+" DATA "+data);

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK)
        {
            mImageUri = data.getData();
            StorageReference filePath = FirebaseStorage.getInstance().getReference().child("Chat_Images"
            ).child(mImageUri.getLastPathSegment());
            Log.d("LOGGED", "ImageURI : " +mImageUri);

            mProgressDialog.setMessage("Uploading...");
            mProgressDialog.show();

            filePath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
            {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                {
                    @SuppressWarnings("VisibleForTests") Uri downloadUri = taskSnapshot.getDownloadUrl();

                    ArrayMap<String, String> map = new ArrayMap<>();
                    map.put("message", downloadUri.toString());
                    map.put("sender", Common.currentUser.getName());
                    myRef.push().setValue(map);
                    myRef2.push().setValue(map);
                    mProgressDialog.dismiss();
                }
            });
        }

        else if (requestCode == 5 && resultCode == RESULT_OK )
        {
            File imgFile = new  File(pictureImagePath);
            if(imgFile.exists()) {
                Log.d("LOGGED", "imgFile : " + imgFile);

                Uri fileUri =Uri.fromFile(imgFile);
                Log.d("LOGGED", "fileUri : " + fileUri);

                StorageReference filePath = FirebaseStorage.getInstance().getReference().child("Chat_Images")
                        .child(fileUri.getLastPathSegment());

                mProgressDialog.setMessage("Uploading...");
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();

                filePath.putFile(fileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        @SuppressWarnings("VisibleForTests") Uri downloadUri = taskSnapshot.getDownloadUrl();
                        ArrayMap<String, String> map = new ArrayMap<>();
                        map.put("message", downloadUri.toString());
                        map.put("sender", Common.currentUser.getName());
                        myRef.push().setValue(map);
                        myRef2.push().setValue(map);

                        mProgressDialog.dismiss();
                    }
                });
            }
        }

        else if (requestCode == 5)
        {
            Toast.makeText(this, "resultCode : "+ resultCode, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Glide.get(getApplicationContext()).clearMemory();
    }

    private void callCamera() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = timeStamp + ".jpg";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        Log.d("LOGGED", "imageFileName :  "+ imageFileName);
        pictureImagePath = storageDir.getAbsolutePath() + "/" + imageFileName;

        File file = new File(pictureImagePath);
        Uri outputFileUri = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getApplicationContext()
                .getPackageName() + ".provider", file);

        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        cameraIntent.putExtra(Intent.EXTRA_RETURN_RESULT, true);
        cameraIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        Log.d("LOGGED", "pictureImagePath :  "+ pictureImagePath);
        Log.d("LOGGED", "outputFileUri :  "+ outputFileUri);
        startActivityForResult(cameraIntent, 5);
    }

    private void callgalary() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_INTENT);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void sendNotificationOrder(final String order_number , final  String order_name)
    {
        DatabaseReference tokens = FirebaseDatabase.getInstance().getReference("Tokens");
        tokens.orderByChild("nameToken").equalTo("Admin Boss")
                .addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot postSnspshot : dataSnapshot.getChildren())
                {
                    Token serverToken = postSnspshot.getValue(Token.class);

                    Notification notification = new Notification(order_number , "Message by "  + order_name );

                    Sender content = new Sender(serverToken.getToken(), notification);

                    mService.sendNotification(content)
                            .enqueue(new Callback<MyResponse>()
                            {
                                @Override
                                public void onResponse(Call<MyResponse> call, Response<MyResponse> response)
                                {
                                    if (response.code() == 200)
                                    {
                                        if (response.body().success == 1)
                                        {
                                            Toast.makeText(MessageList.this,"Message",Toast.LENGTH_SHORT).show();
                                      //      finish();
                                        }
                                        else
                                        {
                                            Toast.makeText(MessageList.this,"Message Failed !",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<MyResponse> call, Throwable t)
                                {
                                    Log.e("ERROR" , t.getMessage());
                                }
                            });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }

}
