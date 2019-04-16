package limited.pvt.global.stachi.glisteruser.app;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import android.provider.Settings.Secure;
import android.os.Handler;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.storage.OnProgressListener;

import limited.pvt.global.stachi.glisteruser.app.Model.Common;
import limited.pvt.global.stachi.glisteruser.app.Model.User;

public class SignUp extends AppCompatActivity
{
    EditText edtPhone, edtName, edtPassword , edtEmail , edtPancard , edtAadharcard , edtEmergencyMobile , edtAddress , edtRefNo , edtRefname;
    Button btnSignUp , btnChoose;

    FirebaseDatabase database;

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView mImageView;
    private Uri mImageUri;

    private StorageReference mStorageRef;
    DatabaseReference table_user ;

    private StorageTask mUploadTask;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = (EditText) findViewById(R.id.edtName);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtPhone = (EditText) findViewById(R.id.edtPhone);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPancard = (EditText) findViewById(R.id.edtPancard);
        edtAadharcard = (EditText) findViewById(R.id.edtAadharcard);
        edtEmergencyMobile = (EditText) findViewById(R.id.edtEmergencyContactNumber);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtRefNo = (EditText) findViewById(R.id.edtReferenceMobileNumber);
        edtRefname = (EditText) findViewById(R.id.edtReferenceName);
        mProgressBar = findViewById(R.id.progress_bar);
        mImageView = (ImageView) findViewById(R.id.imageview);

        table_user = FirebaseDatabase.getInstance().getReference("User");
        mStorageRef = FirebaseStorage.getInstance().getReference("Profile");

        btnChoose = (Button) findViewById(R.id.btnChoose);
        btnChoose.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openFileChooser();
            }
        });

        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                uploadFile();
            }
        });
    }

    private void openFileChooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null)
        {
            mImageUri = data.getData();

            Picasso.with(this).load(mImageUri).into(mImageView);
        }
    }

    private String getFileExtension(Uri uri)
    {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile()
    {
        if (mImageUri != null
                && ! edtPhone.getText().toString().isEmpty()
                && ! edtPassword.getText().toString().isEmpty()
                && ! edtName.getText().toString().isEmpty()
                && ! edtAddress.getText().toString().isEmpty()
                && ! edtEmail.getText().toString().isEmpty()
                && ! edtEmergencyMobile.getText().toString().isEmpty()
                && ! edtPancard.getText().toString().isEmpty()
                && ! edtAadharcard.getText().toString().isEmpty())
        {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + edtPhone.getText().toString()
                    + edtName.getText().toString()
                    + "." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                    {
                        @Override
                        public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot)
                        {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    mProgressBar.setProgress(0);
                                }
                            }, 500);

                            if (Common.isConnectedToInternet(getBaseContext()))
                            {
                                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                                mDialog.setMessage("Please Wait...");
                                mDialog.show();

                                table_user.addValueEventListener(new ValueEventListener()
                                {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot)
                                    {
                                        String android_id = Secure.getString(SignUp.this.getContentResolver(), Secure.ANDROID_ID);

                                        if (dataSnapshot.child(edtPhone.getText().toString()).exists())
                                        {
                                            Toast.makeText(SignUp.this, "Phone Number already registered",
                                                    Toast.LENGTH_SHORT).show();
                                        }

                                        else
                                        {
                                            User upload = new User
                                                    (
                                                            edtName.getText().toString(),
                                                            edtPassword.getText().toString(),
                                                            edtPhone.getText().toString(),
                                                            android_id,
                                                            edtAddress.getText().toString(),
                                                            edtAadharcard.getText().toString(),
                                                            edtPancard.getText().toString(),
                                                            edtEmergencyMobile.getText().toString(),
                                                            edtEmail.getText().toString(),
                                                            edtRefname.getText().toString(),
                                                            edtRefNo.getText().toString(),
                                                            taskSnapshot.getDownloadUrl().toString()
                                                    );

                                            table_user.child(edtPhone.getText().toString()).setValue(upload);

                                            Toast.makeText(SignUp.this, "Sign Up Successfull ! ", Toast.LENGTH_SHORT).show();
                                            finish();

                                            table_user.removeEventListener(this);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError)
                                    {

                                    }
                                });
                            }

                            else
                            {
                                Toast.makeText(SignUp.this, "Check Your Connection", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>()
                    {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot)
                        {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });
        }
        else
            {
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show();
        }
    }
}


