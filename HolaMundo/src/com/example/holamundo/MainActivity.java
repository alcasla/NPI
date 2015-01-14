package com.example.holamundo;

import java.io.File;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity 
{
	private Button btn_takePhoto;  
	private ImageView img;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	    img = (ImageView)this.findViewById(R.id.imageView2);
	    btn_takePhoto = (Button) this.findViewById(R.id.button1);
	    
	    //Añadimos el Listener Boton
	    btn_takePhoto.setOnClickListener(new View.OnClickListener() 
	    {
	    	@Override
	    	public void onClick(View v) 
	    	{
	    		Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);			//Llamamos cámara
	    		
	    		/*
	    		//Almacenar imagen en memoria interna (posible error)
	    		File imagesFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "CameraApp");	
	    		if (! imagesFolder.exists()){
	    	        if (! imagesFolder.mkdirs()){
	    	            Log.d("CameraApp", "failed to create directory");
	    	            System.exit(0);
	    	        }
	    	    }
	    		//imagesFolder.mkdirs();						//Crear carpeta en memoria externa
	    		
	    		File image = new File(imagesFolder.getPath(), "foto.jpg"); 	//Nombre imagen
	    		Uri uriSavedImage = Uri.fromFile(image);
	    		cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
	    		*/
	    		startActivityForResult(cameraIntent, 1);		//Aplicación de cámara con result
	        }});
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		
		if(requestCode==1 && resultCode==RESULT_OK)		//Foto realizada
		{
			//Bitmap bm = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/PhotosCamera/"+"foto.jpg");
			Bundle extras = data.getExtras();
			Bitmap bm = (Bitmap) extras.get("data");
			img.setImageBitmap(bm);			//Mostramos imgen
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
