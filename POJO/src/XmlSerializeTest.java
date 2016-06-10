import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlSerializeTest {

	@Test
	public void pojoToXmlTest() throws JsonGenerationException, JsonMappingException, IOException {
		XmlMapper xmlMapper = new XmlMapper();
		xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
		Human human = new Human();
		xmlMapper.writeValue(new File("otherPerson.json"), human);
		Scanner br = new Scanner(new FileReader("otherPerson.json"));
		String line = "";
		while (br.hasNextLine()) {
		  line += br.nextLine();
		}
		assertEquals("<Human>  <name>Ryan</name>  <age>19</age></Human>", line);
	}
	
	@Test
	public void xmlToPojoTestName() throws JsonParseException, JsonMappingException, IOException {
		XmlMapper xmlMapper = new XmlMapper();
		Human value = xmlMapper.readValue(new File("person.xml"), Human.class);
		System.out.println("hi");
		assertEquals("Lee", value.getName());
	}
	
	@Test
	public void xmlToPojoTestAge() throws JsonParseException, JsonMappingException, IOException {
		XmlMapper xmlMapper = new XmlMapper();
		Human value = xmlMapper.readValue(new File("person.xml"), Human.class);
		assertEquals(2, value.getAge());
	}
	
	@Test
	public void xmlToJson() throws JsonGenerationException, JsonMappingException, IOException {
		XmlMapper xmlMapper = new XmlMapper();
		Human value = xmlMapper.readValue(new File("person.xml"), Human.class);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("xmlToJSON.json"), value);
		Scanner br = new Scanner(new FileReader("xmlToJSON.json"));
		String line = "";
		while (br.hasNextLine()) {
		  line += br.nextLine();
		}
		assertEquals("{\"name\":\"Lee\",\"age\":2}", line);
	}

}