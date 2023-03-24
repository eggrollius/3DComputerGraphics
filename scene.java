import java.awt.Color;

public class scene{
	public lightSource[] lights;
	public sphere[] spheres;
	public Color bgColor;
	
	public scene(){
		this.bgColor = Color.black;
		this.spheres = new sphere[] {new sphere(0, -1, 3, 1,  Color.red), new sphere(2, 0, 4, 1, Color.blue), new sphere(-2, 0, 4, 1, Color.green)};
		this.lights = new lightSource[] {new lightSource(0.2), new lightSource(lightType.POINT, 0.6, 2, 1, 0), new lightSource(lightType.DIRECTIONAL, 0.2, 1, 4, 4)};

	}
}
