package MavenTestNGPractice;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExersiceCalculator {
//    @DataProvider(name = "data-provider2")
//    public Object[][] dpMethod2() {
//        return new Object[][]{{"First-Value"}, {"Second-Value"}};
//    }
//
//    @Test(dataProvider = "data-provider2")
//    public void myTest(String val) {
//        System.out.println("Passed parameter is : " + val);
//    }

    @DataProvider(name = "addition")
    public Object[][] dpMethod() {
        return new Object[][]{{2, 3, 5}, {5, 7, 12}};
    }

    @Test(dataProvider = "addition", groups = "group1")
    public void myTest(int a, int b, int result) {
        int sum = a + b;
        Assert.assertEquals(result, sum);
    }

    @DataProvider(name = "subtraction")
    public Object[][] dpMethod2() {
        return new Object[][]{{12, 5, 7}, {5, 7, -2}};
    }

    @Test(dataProvider = "subtraction", groups = "group2")
    public void myTest2(int a, int b, int result) {
        int sum = a - b;
        Assert.assertEquals(result, sum);
    }

    @DataProvider(name = "multiplication")
    public Object[][] dpMethod3() {
        return new Object[][]{{2, 3, 6}, {5, 7, 35}};
    }

    @Test(dataProvider = "multiplication", groups = "group3")
    public void myTest3(int a, int b, int result) {
        int sum = a * b;
        Assert.assertEquals(result, sum);
    }

    @DataProvider(name = "division")
    public Object[][] dpMethod4() {
        return new Object[][]{{2, 3, 0}, {5, 7, 0}};
    }

    @Test(dataProvider = "division", groups = "group4")
    public void myTest4(int a, int b, int result) {
        int sum = a / b;
        Assert.assertEquals(result, sum);
    }

    @DataProvider(name = "modul")
    public Object[][] dpMethod5() {
        return new Object[][]{{2, 3, 2}, {5, 7, 5}};
    }

    @Test(dataProvider = "modul", groups = "group5")
    public void myTest5(int a, int b, int result) {
        int sum = a % b;
        Assert.assertEquals(result, sum);
    }
}
