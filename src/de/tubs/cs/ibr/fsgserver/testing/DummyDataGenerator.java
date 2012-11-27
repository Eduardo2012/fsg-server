package de.tubs.cs.ibr.fsgserver.testing;



/**
 * Diese Klasse wird nur zum Testen gebraucht, am Ende des Projektes kann sie gelöscht werden.
 * 
 */
public class DummyDataGenerator {

	public static final int DRIVERS_DATA_TYPE = 1;
	public static final int TEAMS_DATA_TYPE =   2;
	
	private static final String DRIVER_XML = "{\"UserID\":18664,\"TeamID\":33," +
			"\"first_name\":\"Alexander\",\"last_name\":\"Mustermann\",\"gender\":0}";
	
	private static final String TEAM_XML =   "{\"TeamID\":33,\"CN\":\"AT\"," +
			"\"cn_short_en\":\"Austria\",\"city\":\"Graz\",\"U\":\"TU\",\"Car\":59," +
			"\"Pit\":49,\"iswaiting\":0,\"class\":1,\"name_pits\":\"TU Graz\"}";
	

	/**
	 * Mit dieser Methode werden Dummy-Daten generiert, die beim Testen der IBR-DTN-Funktionalität
	 * gebraucht werden. Es geht um die Größe der Daten, die transportiert werden sollen.
	 * 
	 * @param amount Anzahl der Dummy-Daten, die generiert werden sollen.
	 * @param dataTyp Hier kann man zurzeit zwischen Fahrer und Teams auswählen ("DRIVERS_DATA_TYPE" und "TEAMS_DATA_TYPE").
	 * @return Die fertig generierte Dummy-Daten in JSON-Format.
	 * @throws Exception Ein Fahler sollte nur dann auftreten, wenn Benutzer der Methode nicht die static Konstanten "DRIVERS_DATA_TYPE" und "TEAMS_DATA_TYPE" benutzen sondern selbst einen Integer-Wert angeben.
	 */
	public static String createDataSet(int amount, int dataTyp) throws Exception{
		StringBuilder result = new StringBuilder();
		result.append("[");
		String data_xml = "";
		
		if (dataTyp==DummyDataGenerator.DRIVERS_DATA_TYPE){
			data_xml= DRIVER_XML;
		}else if(dataTyp==DummyDataGenerator.TEAMS_DATA_TYPE){
			data_xml= TEAM_XML;
		}else{
			throw new Exception("Weder Fahrer noch Teams ausgewählt, bitte ihre dataTyp-Angabe überprüfen!");
		}
		
		for(int i=0; i<amount; i++){
			result.append(data_xml);
			if (i<amount-1){
				result.append(",");
			}
		}
		
		result = result.append("]");
		return result.toString();
	}
	

	
}
