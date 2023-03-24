import java.lang.Math.*;

public class vec3{
	double x;
	double y;
	double z;

	public vec3(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double dot(vec3 other){
		return this.x * other.x + this.y * other.y + this.z * other.z;
	}

	public vec3 add(vec3 other){
		return new vec3(this.x + other.x, this.y + other.y, this.z + other.z);
	}

	public vec3 minus(vec3 other){
		return new vec3(this.x - other.x, this.y - other.y, this.z - other.z);
	}

	public void Scale(double scalar){
		this.x *= scalar;
		this.y *= scalar;
		this.z *= scalar;
		return;
	}

	public vec3 ScalarMultiply(double scalar){
		return new vec3(this.x * scalar, this.y * scalar, this.z * scalar);
	}

	public double getLength(){
		return Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
	}
}
