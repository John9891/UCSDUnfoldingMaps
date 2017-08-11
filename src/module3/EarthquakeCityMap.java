package module3;

//Java utilities libraries
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

//Processing library
import processing.core.PApplet;

//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

//Parsing library
import parsing.ParseFeed;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 * Date: July 17, 2015
 * */
public class EarthquakeCityMap extends PApplet {

	// You can ignore this.  It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = true;
	
	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 5;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 4;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	// The map
	private UnfoldingMap map;
	
	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

	
	public void setup() {
		size(950, 600, OPENGL);

		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2017_30_05.atom"; 	// Same feed, saved Aug 7, 2015, for working offline
		}
		else {
			map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			//earthquakesURL = "2.5_week.atom";
		}
		
	    map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map);	
			
	    // The List you will populate with new SimplePointMarkers
	    List<Marker> markers = new ArrayList<Marker>();

	    //Use provided parser to collect properties for each earthquake
	    //PointFeatures have a getLocation method
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    
	    // These print statements show you (1) all of the relevant properties 
	    // in the features, and (2) how to get one property and use it
	    /*if (earthquakes.size() > 0) {
	    	PointFeature f = earthquakes.get(0);
	    	System.out.println(f.getProperties());
	    	Object magObj = f.getProperty("magnitude");
	    	float mag = Float.parseFloat(magObj.toString());
	    	// PointFeatures also have a getLocation method
	    }*/
	    
	    List<PointFeature> bigEarthquakes = new ArrayList<PointFeature>();
	    
	    if (earthquakes.size() > 0){
	    	System.out.println(earthquakes.size());
	    	for(int i=0; i<earthquakes.size();i++){
	    		PointFeature f = earthquakes.get(i);	
	    		System.out.println(f.getProperty("magnitude"));
	    		System.out.println(f.getProperties());
	    		bigEarthquakes.add(f);
	    	}
	    }
	    
	    //TODO: Add code here as appropriate
	    /*PointFeature kamchatkaEq = new PointFeature(new Location(52.76f,160.06f));
	    kamchatkaEq.addProperty("title", "Kamchatka");
	    kamchatkaEq.addProperty("magnitude", "3.0");
	    kamchatkaEq.addProperty("date", "November 4, 1952");
	    kamchatkaEq.addProperty("year", 1952);	    
	    
	    bigEarthquakes.add(valdiviaEq);
	    bigEarthquakes.add(alaskaEq);
	    bigEarthquakes.add(sumatraEq);
	    bigEarthquakes.add(japanEq);
	    bigEarthquakes.add(kamchatkaEq);*/    
	    
	    for (PointFeature eq: bigEarthquakes) {
	    	markers.add(new SimplePointMarker(eq.getLocation(), eq.getProperties()));
	    }
	    map.addMarkers(markers);
	    
	    int blue = color(0,0,255);
	    int yellow = color(255, 255, 0);
	    int red = color(255,0,0);
	    
	    /*for (Marker mk :markers) {
	    	if ((Double.parseDouble((String) mk.getProperty("magnitude")))<4.0){
	    		mk.setColor(blue);
	    	}
	    	else if((Double.parseDouble((String) mk.getProperty("magnitude")))>5.0){
	    		mk.setColor(red);
	    	}
	    	else{
	    		mk.setColor(yellow);
	    	}
	    }*/
	    for (Marker mk :markers) {
	    	if ((float)mk.getProperty("magnitude")<4.0){
	    		mk.setColor(blue);
	    	}
	    	else if((float) mk.getProperty("magnitude")>5.0){
	    		mk.setColor(red);
	    	}
	    	else{
	    		mk.setColor(yellow);
	    	}
	    }
	}
		
	// A suggested helper method that takes in an earthquake feature and 
	// returns a SimplePointMarker for that earthquake
	// TODO: Implement this method and call it from setUp, if it helps
	private SimplePointMarker createMarker(PointFeature feature)
	{
		// finish implementing and use this method, if it helps.
		return new SimplePointMarker(feature.getLocation());
	}
	
	public void draw() {
	    background(100);
	    map.draw();
	    addKey();
	}


	// helper method to draw key in GUI
	// TODO: Implement this method to draw the key
	private void addKey() 
	{	
		// Remember you can use Processing's graphics methods here
		rect(30, 50, 150, 250);
		String s = "Earthquake Key";		
		textSize(14);
		textAlign(CENTER);
		fill(0, 0, 0);
		text(s, 105, 80); 		
		
		fill(255,0,0);
		ellipse(40, 135, 15, 15);
		textSize(14);
		textAlign(CENTER);
		fill(0, 0, 0);
		text("5.0 + Magnitude", 115, 140);
		
		fill(255,255,0);
		ellipse(40, 175, 10, 10);
		textSize(14);
		textAlign(CENTER);
		fill(0, 0, 0);
		text("4.0 + Magnitude", 115, 180);
		
		fill(0,0,255);
		ellipse(40, 215, 5, 5);
		textSize(14);
		textAlign(CENTER);
		fill(0, 0, 0);
		text("Below 4.0", 115, 220);
		fill(255, 255,255,255);	
		
		
		
		
	
	}
}
