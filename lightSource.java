enum lightType{
	AMBIENT,
	POINT,
	DIRECTIONAL,
}

public class lightSource {
	lightType type;
	double intensity;
	vec3 position = null;
	vec3 direction = null;
	public lightSource(double intensity){
		this.type = lightType.AMBIENT;
		this.intensity = intensity;
	}

	public lightSource(lightType type, double intensity, vec3 vector){
		this.type = type;
		this.intensity = intensity;
		if(this.type == lightType.AMBIENT){
			position = null;
		}
		if(this.type == lightType.POINT){
			position = vector;
		}
		if(this.type == lightType.DIRECTIONAL){
			this.direction = vector; 
		}

	}
		
	public lightSource(lightType type, double intensity, double x, double y, double z){
		this.type = type;

		this.intensity = intensity;
		
		if(this.type == lightType.AMBIENT){
			position = null;
		}
		if(this.type == lightType.POINT){
			position = new vec3(x, y, z);
		}
		if(this.type == lightType.DIRECTIONAL){
			this.direction = new vec3(x, y, z);
		}

	}
}
