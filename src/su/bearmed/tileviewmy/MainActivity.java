package su.bearmed.tileviewmy;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.qozix.mapview.MapView;

public class MainActivity extends Activity 
{

	private MapView mapView;
	
	    @Override
	    protected void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
	
	        mapView = new MapView( this );
	        
	        setContentView( mapView );
	
	        // Set the minimum parameters
	        mapView.setSize(8192,8192);
	        mapView.addZoomLevel(1024,1024, "tiles/125_%col%_%row%.png", "downsamples/map.png" );
	        mapView.addZoomLevel(2048,2048, "tiles/250_%col%_%row%.png", "downsamples/map.png" );
	        mapView.addZoomLevel(4096,4096, "tiles/500_%col%_%row%.png", "downsamples/map.png" );
	        mapView.addZoomLevel(8192,8192, "tiles/1000_%col%_%row%.png", "downsamples/map.png" );
	        
	        // Add the view to display it
	        setContentView(mapView);
	        
	        mapView.registerGeolocator( 85.05, -179.82, -84.91, 179.46);
	        
	        ImageView marker = new ImageView( this );
			// save the coordinate for centering and callout positioning
			marker.setTag("Минск");
			// give it a standard marker icon - this indicator points down and is centered, so we'll use appropriate anchors
			marker.setImageResource( R.drawable.ic_launcher );
			mapView.addMarker(marker, 53.54, 27.34);

	        
	        mapView.setCacheEnabled( true );
	        
	        
	    }
	    
	 // on pause let's clear the tiles bitmap data
		@Override
		public void onPause()
		{
			super.onPause();
			mapView.clear();
		}
		
		// on resume, get a new render
		@Override
		public void onResume()
		{
			super.onResume();
			mapView.requestRender();
		}

		// on destroy, clear and recycle bitmap data, and set all references to null
		@Override
		public void onDestroy()
		{
			super.onDestroy();
			mapView.destroy();
			mapView = null;
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) 
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
