import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SecondTestClass {
    @BeforeTest(alwaysRun = true)
    public void setup2nd() {
        System.out.println("This is 2nd Before Test");
    }
    @Test
    public void testDemoOne2nd(){
        System.out.println("This is my 1st TestNG test");
    }
    @Test
    public void testDemoTwo2nd(){
        System.out.println("This is my 2nd TestNG test");
    }

    @AfterTest(alwaysRun = true)
    public void cleanUp2nd(){
        System.out.println("This is 2nd After Test");
    }
}
