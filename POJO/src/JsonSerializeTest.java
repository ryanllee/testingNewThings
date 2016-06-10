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

public class JsonSerializeTest {

	@Test
	public void pojoToJsonTest() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		Human human = new Human();
		mapper.writeValue(new File("tester.json"), human);
		Scanner br = new Scanner(new FileReader("tester.json"));
		String line = "";
		while (br.hasNextLine()) {
		  line += br.nextLine();
		}
		System.out.println(line);
		assertEquals("{  \"name\" : \"Ryan\",  \"age\" : 19}", line);
	}
	
	@Test
	public void jsonToPojoTestName() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Human jsonHuman = mapper.readValue(new File("practice.json"), Human.class); 
		assertEquals("Ryan", jsonHuman.getName());
	}

	@Test
	public void jsonToPojoTestAge() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Human jsonHuman = mapper.readValue(new File("practice.json"), Human.class); 
		assertEquals(21, jsonHuman.getAge());
	}
	
	@Test
	public void jsonToXML() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Human jsonHuman = mapper.readValue(new File("practice.json"), Human.class); 
		System.out.println(jsonHuman.getName());
		System.out.println(jsonHuman.getAge());
		XmlMapper xmlMapper = new XmlMapper();
		xmlMapper.writeValue(new File("jsonToXML.xml"), jsonHuman);
		Scanner br = new Scanner(new FileReader("jsonToXML.xml"));
		String line = "";
		while (br.hasNextLine()) {
		  line += br.nextLine();
		}
		assertEquals("<Human><name>Ryan</name><age>21</age></Human>", line);
	}

}