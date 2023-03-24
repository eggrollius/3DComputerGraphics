import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math.*;

public class main{
	private static JFrame theWindow;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	private static panel thePanel;
	public static Color[] pixelBuffer = new Color[WIDTH * HEIGHT];
	public static Color bgColor = Color.black;
	public static int viewport_width;
	public static int viewport_height;
	public static int viewport_size;
	public static int projection_plane_d;
	public static double inf = Double.POSITIVE_INFINITY;
	public static scene theScene;
	private static void createAndShowGUI(){
		thePanel = new panel();
		
		theWindow = new JFrame("3D Graphics App");
		theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theWindow.setSize(WIDTH, HEIGHT);


		theWindow.setLocationRelativeTo(null);
		theWindow.setResizable(false);


		theWindow.add(thePanel);

		theWindow.setVisible(true);
	}

	public static vec3 CanvasToViewport(int x, int y){
		return new vec3(((double)(x*viewport_width))/WIDTH,((double)(y*viewport_height))/HEIGHT, projection_plane_d);
	}

	public static void clearScreen(Color color){
		for(int i = 0; i < WIDTH*HEIGHT; i++){
			pixelBuffer[i] = color;
		}

		return;
	}

	public static double ComputeLighting(vec3 P, vec3 N){
		double i = 0.0;
		vec3 L;
		for(lightSource light: theScene.lights){
			if(light.type == lightType.AMBIENT){
				i += light.intensity;
			}
			else{
				if(light.type == lightType.POINT){
					L = light.position.minus(P);
				}
				else {
					L = light.direction;
				}

				double N_dot_L = N.dot(L);
				if(N_dot_L > 0){
					i += light.intensity * N_dot_L/(N.getLength() * L.getLength());
				}
			}
		}

		return i;
	}

	public static Color TraceRay(vec3 O, vec3 D, double t_min, double t_max){
		double closest_t = inf;
		sphere closest_sphere = null;

		for(sphere s : theScene.spheres){
			double[] t1t2 = IntersectRaySphere(O, D, s);
			double t1 = t1t2[0];
			double t2 = t1t2[1];
			if(t1 > t_min && t1 < t_max && t1 < closest_t){
				closest_t = t1;
				closest_sphere = s;
			}	
			if(t2 > t_min && t2 < t_max && t2 < closest_t){
				closest_t = t2;
				closest_sphere = s;
			}
		}

		if(closest_sphere == null){
			return bgColor;
		}
		
		vec3 P = O.add(D.ScalarMultiply(closest_t));
		vec3 N = (P.minus(closest_sphere.center));
		N.Scale(1/N.getLength());
		double intensity = ComputeLighting(P, N);
		int r = closest_sphere.color.getRed();
		int g = closest_sphere.color.getGreen();
		int b = closest_sphere.color.getBlue();

		return new Color((int)(intensity*r), (int)(intensity*g), (int)(intensity*b));
	}

	public static double[] IntersectRaySphere(vec3 O, vec3 D, sphere sphere){
		double radius = sphere.radius;
		vec3 CO = O.minus(sphere.center);
		double a = D.dot(D);
		double b = 2*CO.dot(D);
		double c = CO.dot(CO) - radius*radius;
		double discriminant = b*b - 4*a*c;
		if(discriminant < 0){
			return new double[] {inf, inf};
		}
		return new double[]{(-b + Math.sqrt(discriminant)) / (2*a), (-b - Math.sqrt(discriminant)) / (2*a)};	
	}
	public static void main(String[] args){
		//Setup the variables and the scene
		vec3 O = new vec3(0, 0, 0);//Origin, camera	
		viewport_width = 1;
		viewport_height = 1;
		viewport_size = viewport_width*viewport_height;
		projection_plane_d = 1;
		
		theScene = new scene();	
		//Create a window that uses the color buffer to draw pixels
		createAndShowGUI();

		//do graphics calculations
		//for every pixel on screen width
		
		for(int x = -WIDTH/2; x < WIDTH/2; x++){
			for(int y = -HEIGHT/2; y < HEIGHT/2; y++){
				vec3 D = CanvasToViewport(x, y);
				Color color = TraceRay(O, D, 1, inf);
				//set corresponding pixel to color in the color buffer
				pixelBuffer[(x+WIDTH/2) + (-(y+1)+HEIGHT/2)*WIDTH] = color;	
			}
		}
	}

}
