package module6;

import java.util.List;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import processing.core.PConstants;
import processing.core.PGraphics;

/** 
 * A class to represent AirportMarkers on a world map.
 *   
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMarker extends CommonMarker {
	public static List<SimpleLinesMarker> routes;
	
	public AirportMarker(Feature city) {
		super(((PointFeature)city).getLocation(), city.getProperties());
	
	}
	
	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		pg.fill(0, 255, 0);
		pg.ellipse(x, y, 10, 10);
		
		
	}
	
	public void showTitle(PGraphics pg, float x, float y) {
		//System.out.println(getProperties());
		//System.out.println(getCountry());	
		
		String name = getCountry() + " - " + getCity() + " ";
		String pop = "Altitude: " + getAltitude() + " fts";			
		pg.pushStyle();			
		pg.fill(0, 255, 0);
		pg.textSize(12);
		pg.rectMode(PConstants.CORNER);
		pg.rect(x, y-36, Math.max(pg.textWidth(name), pg.textWidth(pop)) + 6, 30);
		pg.fill(0, 0, 0);
		pg.textAlign(PConstants.LEFT, PConstants.TOP);
		pg.text(name, x+3, y-33);
		pg.text(pop, x+3, y -22);		
		pg.popStyle();
			
		
		
	}
	
	private String getCountry(){
		return getStringProperty("country");
	}
	
	private int getAltitude(){
		return Integer.parseInt(getStringProperty("altitude"));
	}
	
	private String getCity(){
		return getStringProperty("city");
	}
	
	
	
}
