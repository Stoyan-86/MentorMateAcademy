import org.testng.annotations.*;

public class TestNGDemo {

    @BeforeTest(alwaysRun = true)
    public void setup() {
        System.out.println("This is Before Test");
    }

    @Test (groups = "group1", dependsOnGroups = "group2")
    public void testDemoOne() {
        System.out.println("This is my 1st TestNG test");
    }

    @Test (groups = "group2")
    public void testDemoTwo() {
        System.out.println("This is my 2nd TestNG test");
    }

    @AfterTest(alwaysRun = true)
    public void cleanUp() {
        System.out.println("This is After Test");
    }
}