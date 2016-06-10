import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Human implements Serializable{
	
	private static final long serialVersionUID = -2028475672954357127L;

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("age")
	private int age;
	
	public Human(){
		name = "Ryan";
		age = 19;
	}
	
	public String getName(){
		return name;
	}
	
	public int getAge(){
		return age;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setAge(int age){
		this.age = age;
	}
}
