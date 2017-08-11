package module4;

import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.PGraphics;

/** Implements a visual marker for ocean earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 *
 */
public class OceanQuakeMarker extends EarthquakeMarker {
	
	PointFeature quake;
	
	public OceanQuakeMarker(PointFeature quake) {
		super(quake);
		this.quake = quake;
		// setting field in earthquake marker
		isOnLand = false;
	}
	
	public void drawEarthquake(PGraphics pg, float x, float y) {
		// Drawing a centered square for Ocean earthquakes
		// DO NOT set the fill color.  That will be set in the EarthquakeMarker
		// class to indicate the depth of the earthquake.
		// Simply draw a centered square.
		
		// HINT: Notice the radius variable in the EarthquakeMarker class
		// and how it is set in the EarthquakeMarker constructor
		
		// TODO: Implement this method
		pg.rect(x-(float)quake.getProperty("magnitude")*1.5f, y+(float)quake.getProperty("magnitude")*1.5f,
				(float)quake.getProperty("magnitude")*3, (float)quake.getProperty("magnitude")*3);
		
		
	}
	


	

}
