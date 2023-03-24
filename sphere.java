import java.awt.Color;

public class sphere{
	vec3 center;
	double radius;
	Color color;	
	public sphere(){
		this.center = new vec3(0, 0, 0);
		this.radius = 1;
		this.color = Color.white;
	}

	public sphere(double x, double y, double z, double radius, Color color){
		this.center = new vec3(x, y, z);
		this.radius = radius;
		this.color = color;
	}
		
}
