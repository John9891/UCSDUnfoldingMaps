
public class ArrayLocation {

	public static void main(String[] args) {
		
		double coords [] = {5.0, 0.0};
		Location accra = new Location(coords);
		coords[0] = 32.9;
		coords[1] = -117.2;
		System.out.println(accra.coords[0]);
	}

}

class Location{
	
	double coords[];
	
	public Location(double coords[]){
		
		this.coords = coords;
	}	
	
}