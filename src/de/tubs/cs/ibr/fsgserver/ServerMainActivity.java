package de.tubs.cs.ibr.fsgserver;

import de.tubs.cs.ibr.fsgserver.R;
import de.tubs.cs.ibr.fsgserver.testing.DummyDataGenerator;
import de.tubs.ibr.dtn.api.Block;
import de.tubs.ibr.dtn.api.DTNClient;
import de.tubs.ibr.dtn.api.DataHandler;
import de.tubs.ibr.dtn.api.GroupEndpoint;
import de.tubs.ibr.dtn.api.Registration;
import de.tubs.ibr.dtn.api.ServiceNotAvailableException;
import de.tubs.ibr.dtn.api.SingletonEndpoint;
import de.tubs.ibr.dtn.api.TransferMode;
import de.tubs.ibr.dtn.api.DTNClient.Session;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class ServerMainActivity extends Activity {

	private final static String TAG = "FSG-Server";
	
	//////////////////////////////////////////////
	// Benötigt fürs SENDEN
	// DTN client to talk with the DTN service
	private DTNClient _client = null;
	private Registration _registration = null;

	

	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        getWindow().setBackgroundDrawableResource(R.drawable.background_image);
        
    
        
        Log.i(TAG, "MainActivity created.");
        

        _client = new DTNClient();
        
		// create registration
		_registration = new Registration("fsg");
    	
		// register own data handler for incoming bundles
		_client.setDataHandler(_data_handler);

		Log.i(TAG, "activity created");
		
		try {
			_client.initialize(this, _registration);
			Log.i(TAG, "onCreate are done without errors!");
		} catch (ServiceNotAvailableException e) {
			Log.e(TAG, "ServiceNotAvailableException");
		} catch (SecurityException ex) {
			Log.e(TAG, "SecurityException");
		}
		
		
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
	
	@Override
	protected void onDestroy() {

		// unregister at the daemon
		_client.terminate();
		_client = null;
		
		super.onDestroy();
		
		Log.i(TAG, "activity destroyed");
	}
    
    
    /**
     * 
     * @param view
     */
    public void onButtonClick(View view){
    	switch (view.getId() ){
    	case R.id.button1:
    		try {
    			Log.i(TAG, "Button 1 clicked");
    			String teams = DummyDataGenerator.createDataSet(200, DummyDataGenerator.TEAMS_DATA_TYPE);
				this.sendMessage(teams);
			} catch (Exception e) {
				e.printStackTrace();
				Log.i(TAG, "ERROR during sending message!");
			}
    		break;
    	case R.id.button2:
    		try {
    			Log.i(TAG, "Button 2 clicked");
    			String drivers = DummyDataGenerator.createDataSet(1000, DummyDataGenerator.DRIVERS_DATA_TYPE);
				this.sendMessage(drivers);
			} catch (Exception e) {
				e.printStackTrace();
				Log.i(TAG, "ERROR during sending message!");
			}
    		break;
    	case R.id.button3:
    		try {
    			Log.i(TAG, "Button 3 clicked");
				this.sendMessage("{\"Classes\":[{\"name\":\"FSC\",\"EventID\":9},{\"name\":\"FSE\",\"EventID\":10}]}");
			} catch (Exception e) {
				e.printStackTrace();
				Log.i(TAG, "ERROR during sending message!");
			}
    		break;
    	case R.id.button4:
    		try {
    			Log.i(TAG, "Button 4 clicked");
				this.sendMessage("[{\"DeviceID\":1111,\"Timestamp\":\"1353949660\"},{\"DeviceID\":2222,\"Timestamp\":\"1353946060\"},{\"DeviceID\":3333,\"Timestamp\":\"1353942460\"},{\"DeviceID\":4444,\"Timestamp\":\"1353938860\"},{\"DeviceID\":5555,\"Timestamp\":\"1353935260\"},{\"DeviceID\":6666,\"Timestamp\":\"1353931660\"}]");
			} catch (Exception e) {
				e.printStackTrace();
				Log.i(TAG, "ERROR during sending message!");
			}
    		break;
    	case R.id.button5:
    		try {
    			Log.i(TAG, "Button 5 clicked");
				this.sendMessage("[{\"TagID\":111},{\"TagID\":222},{\"TagID\":333},{\"TagID\":444}]"	);
			} catch (Exception e) {
				e.printStackTrace();
				Log.i(TAG, "ERROR during sending message!");
			}
    		break;
    	}
    }
    


	
	public void sendMessage(String msg) throws Exception
	{
		Session s = _client.getSession();
		
		//SingletonEndpoint destination = new SingletonEndpoint("dtn://android-e782a13f.dtn/fsg");
		//SingletonEndpoint destination = new SingletonEndpoint("dtn://android-7bfb105d.dtn/fsg");
		GroupEndpoint destination = new GroupEndpoint("dtn://fsg.dtn/broadcast");
		
		if (!s.send(destination, 600, msg.getBytes())){
			throw new Exception("Can not send the JSON-Data");
		}else{
			Log.i(TAG, "JSON-Data sended.");
		}
		
	}

	
	private DataHandler _data_handler = new DataHandler() {

		@Override
		public void startBundle(de.tubs.ibr.dtn.api.Bundle bundle) {
		}

		@Override
		public void endBundle() {
			
		}

		@Override
		public TransferMode startBlock(Block block) {
			return TransferMode.NULL;
		}

		@Override
		public void endBlock() {
	
		}

		@Override
		public void characters(String data) {
			Log.i(TAG, "Received characters: " + new String(data));
		}

		@Override
		public ParcelFileDescriptor fd() {
			
			return null;
		}

		@Override
		public void payload(byte[] data) {
			Log.i(TAG, "Received payload: " + new String(data));
		}

		@Override
		public void progress(long current, long length) {
			Log.i(TAG, "Payload: " + current + " of " + length + " bytes.");
		}

		@Override
		public void finished(int startId) {
		}		
	};
	
	
	
}
