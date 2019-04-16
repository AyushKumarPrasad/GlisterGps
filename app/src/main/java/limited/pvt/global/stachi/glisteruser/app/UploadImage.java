package limited.pvt.global.stachi.glisteruser.app;


import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import limited.pvt.global.stachi.glisteruser.app.Model.Common;
import limited.pvt.global.stachi.glisteruser.app.Model.Upload;


public class UploadImage extends AppCompatActivity
{
    private Button  mUploadBtn ,upload  ;
    private ImageView mImageView ;

    private static final int CAMERA_REQUEST_CODE = 1 ;
    private StorageReference mStorage ;
    private DatabaseReference mDatabaseRef;
    private ProgressDialog mProgress  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

  /*      String ayush = Common.currentUser.getPhone();

        mStorage = FirebaseStorage.getInstance().getReference("uploads/");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Images").child(ayush);

        mUploadBtn = (Button) findViewById(R.id.upload);
        upload = (Button) findViewById(R.id.upload1);
        mImageView = (ImageView) findViewById(R.id.imageview);

        mProgress = new ProgressDialog(this);

        mUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent , CAMERA_REQUEST_CODE);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            mImageView.setImageBitmap(bitmap);

            String kumar = Common.currentUser.getName();

            String path = "images/" + System.currentTimeMillis() + kumar;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
            byte[] data1 = baos.toByteArray();
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageReference = storage.getReference();
            StorageReference reference = storageReference.child(path);
            UploadTask uploadTask = reference.putBytes(data1);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                    Toast.makeText(UploadImage.this,"Upload Successful",Toast.LENGTH_SHORT).show();
                    Upload upload = new Upload(Common.currentUser.getName(),
                            taskSnapshot.getDownloadUrl().toString());

                    String uploadId = mDatabaseRef.push().getKey();
                    mDatabaseRef.child(uploadId).setValue(upload);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

            if (mImageView != null)
            {

            }


        }  */
    }
}





/*       Uri uri = data.getData();

            String kumar = Common.currentUser.getPhone();

            StorageReference filepath = mStorage.child(System.currentTimeMillis()+"PHONE :" + kumar
                    + "." +getFileExtension(uri));
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    mProgress.dismiss();
                    Uri downloadUri = taskSnapshot.getDownloadUrl();
                    Picasso.with(UploadImage.this).load(downloadUri).fit().centerCrop().into(mImageView);
                    Toast.makeText(UploadImage.this, "Uploading Finished...", Toast.LENGTH_SHORT).show();
                }
            }); */