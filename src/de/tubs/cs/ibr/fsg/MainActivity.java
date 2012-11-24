package de.tubs.cs.ibr.fsg;

import de.tubs.ibr.dtn.api.Block;
import de.tubs.ibr.dtn.api.DTNClient;
import de.tubs.ibr.dtn.api.DataHandler;
import de.tubs.ibr.dtn.api.GroupEndpoint;
import de.tubs.ibr.dtn.api.Registration;
import de.tubs.ibr.dtn.api.ServiceNotAvailableException;
import de.tubs.ibr.dtn.api.TransferMode;
import de.tubs.ibr.dtn.api.DTNClient.Session;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	private final static String TAG = "MainActivity";
	
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

		Log.d(TAG, "activity created");
		
		try {
			_client.initialize(this, _registration);
			Log.d(TAG, "onCreate are done without errors!");
		} catch (ServiceNotAvailableException e) {
			Log.d(TAG, "ServiceNotAvailableException");
		} catch (SecurityException ex) {
			Log.d(TAG, "SecurityException");
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
		_client.unregister();
		_client.terminate();
		_client = null;
		
		super.onDestroy();
		
		Log.d(TAG, "activity destroyed");
	}
    
    
    /**
     * 
     * @param view
     */
    public void onButtonClick(View view){
    	switch (view.getId() ){
    	case R.id.button1:
    		try {
				this.sendMessage(
						"[{\"TeamID\":33,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"TU\",\"Car\":59,\"Pit\":49,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Graz\"}," +
						
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"Monash University\"}]");
			} catch (Exception e) {
				e.printStackTrace();
				Log.i(TAG, "ERROR during sending message!");
			}
    		break;
    	case R.id.button2:
    		try {
				this.sendMessage(
						"[{\"UserID\":18664,\"TeamID\":33,\"first_name\":\"Alexander\",\"last_name\":\"Mustermann\",\"gender\":0}," +
						"{\"UserID\":18618,\"TeamID\":33,\"first_name\":\"Augustin\",\"last_name\":\"Mustermann\",\"gender\":0}," +
						"{\"UserID\":7806,\"TeamID\":54,\"first_name\":\"John\",\"last_name\":\"Doe\",\"gender\":0}]");
			} catch (Exception e) {
				e.printStackTrace();
				Log.i(TAG, "ERROR during sending message!");
			}
    		break;
    	case R.id.button3:
    		try {
				this.sendMessage("{\"Classes\":[{\"name\":\"FSC\",\"EventID\":9},{\"name\":\"FSE\",\"EventID\":10}]}");
			} catch (Exception e) {
				e.printStackTrace();
				Log.i(TAG, "ERROR during sending message!");
			}
    		break;
    	case R.id.button4:
    		try {
				this.sendMessage("...Hier sollte eine Blacklist stehen!...");
			} catch (Exception e) {
				e.printStackTrace();
				Log.i(TAG, "ERROR during sending message!");
			}
    		break;
    	case R.id.button5:
    		try {
				this.sendMessage(						"[{\"TeamID\":33,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"TU\",\"Car\":59,\"Pit\":49,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Graz\"}," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"{\"TeamID\":52,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Graz\",\"U\":\"UAS\",\"Car\":107,\"Pit\":41,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"UAS Joanneum Graz\"}," +
						
						"{\"TeamID\":125,\"CN\":\"AT\",\"cn_short_en\":\"Austria\"," +
						"\"city\":\"Wien\",\"U\":\"TU\",\"Car\":41,\"Pit\":73,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"TU Vienna\"}," +
						
						"{\"TeamID\":54,\"CN\":\"AU\",\"cn_short_en\":\"Australia\"," +
						"\"city\":\"Melbourne\",\"U\":\"Monash\",\"Car\":66,\"Pit\":54,\"iswaiting\":0," +
						"\"class\":1,\"name_pits\":\"Monash University\"}]");
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
		//SingletonEndpoint destination = new SingletonEndpoint("dtn://android-db84495e.dtn/fsg");
		GroupEndpoint destination = new GroupEndpoint("dtn://fsg.dtn/broadcast");
		
		if (!s.send(destination, 2400, msg.getBytes())){
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
