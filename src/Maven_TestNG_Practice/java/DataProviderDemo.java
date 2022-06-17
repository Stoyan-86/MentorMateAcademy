import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderDemo {
//    @DataProvider(name = "data-provider2")
//    public Object[][] dpMethod2() {
//        return new Object[][]{{"First-Value"}, {"Second-Value"}};
//    }
//
//    @Test(dataProvider = "data-provider2")
//    public void myTest(String val) {
//        System.out.println("Passed parameter is : " + val);
//    }

    @DataProvider(name = "data-provider")
    public Object[][] dpMethod() {
        return new Object[][]{{2, 3, 5}, {5, 7, 9}};
    }

    @Test(dataProvider = "data-provider")
    public void myTest(int a, int b, int result) {
        int sum = a + b;
        Assert.assertEquals(result, sum);
    }
}
