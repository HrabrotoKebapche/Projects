import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.chrome.ChromeDriver; 
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.*;

public class Login {
public static void main(String[] args) {

	 System.setProperty("webdriver.chrome.driver", "C:\\Users\\kristian\\Downloads\\chromedriver.exe");        
	 WebDriver driver = new ChromeDriver(); 
   
	 
     //Open gmail
     driver.get("http://www.gmail.com");
      
     // Enter userd id
     WebElement element = driver.findElement(By.id("Email"));
    
     element.sendKeys("hrabrotokebapche@gmail.com");
     element.submit();
      
     //wait 5 secs for  userid to be entered
     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
      System.out.println("opaaaaaaaaaaaaaaaaaa");
     //Enter Password
     WebElement element1 = driver.findElement(By.id("Passwd"));
     WebElement element2 = driver.findElement(By.id("signIn"));
     element1.sendKeys("kolko1sym1prost");
     element2.click();
     
     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
     if(driver.getPageSource().contains("Loading")){
    	 System.out.println("logged!!!");
    	
     }else{
    	 System.out.println("didnt logged!!!");
     }
      
}

}


