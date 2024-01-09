package assignment;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CAWAssignment {
	public static void main(String[] args) throws InterruptedException, IOException {

		String filePath = "src/input.json";
		ObjectMapper objectMapper = new ObjectMapper();
		File jsonFile = new File(filePath);
		String jsonString = objectMapper.readTree(jsonFile).toString();

		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\Downloads\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		String baseUrl = "https://testpages.herokuapp.com/styled/tag/dynamic-table.html";
		driver.get(baseUrl);

		driver.findElement(By.xpath("//*[text()='Table Data']")).click();
		driver.findElement(By.xpath("//*[@id=\"jsondata\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"jsondata\"]")).sendKeys(jsonString);
		driver.findElement(By.xpath("//button[@id=\"refreshtable\"]")).click();
		
		

            JsonNode jsonArray = objectMapper.readTree(jsonFile);

            int i=2;
            for (JsonNode element : jsonArray) {
                String name = element.get("name").asText();
                String age = element.get("age").asText();
                String gender = element.get("gender").asText();

                System.out.println("JSON Name: " + name + ", Age: " + age + ", Gender: " + gender);
                
                String nameInTable =driver.findElement(By.xpath("//table[@id=\"dynamictable\"]/tr["+i+"]/td["+1+"]")).getText();
                String ageInTable =driver.findElement(By.xpath("//table[@id=\"dynamictable\"]/tr["+i+"]/td["+2+"]")).getText().trim();
                String genderInTable = driver.findElement(By.xpath("//table[@id=\"dynamictable\"]/tr["+i+"]/td["+3+"]")).getText();
                
                System.out.println("WEBPAGE Name: " + nameInTable + ", Age: " + ageInTable + ", Gender: " + genderInTable);

                assertTrue(name.equals(nameInTable));
                assertTrue(age.equals(ageInTable));
                assertTrue(gender.equals(genderInTable));

                i++;
                
            }
 		driver.quit();

	}
}
