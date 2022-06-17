package MavenTestNGPractice;

import org.testng.annotations.Test;

import java.io.IOException;

public class Exeptions {
    @Test(groups = "example4", expectedExceptions = RuntimeException.class)
    public void throwExceptionExample() {
        System.out.println("Runtime exception is triggered");
        throw new RuntimeException("Runtime Exception");
    }

    @Test(expectedExceptions = {IOException.class}, expectedExceptionsMessageRegExp = "Pass Massage test")
    public void exceptionTestOne() throws Exception {
        throw new IOException("Pass Massage test");
    }

    @Test(expectedExceptions = {IOException.class}, expectedExceptionsMessageRegExp = "Pass Massage test")
    public void exceptionTessTwo() throws Exception {
        throw new IOException("Fail Massage test");
    }
}
