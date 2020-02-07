package actions;

import base.Keywords;
import base.Test;
import exceptions.ApplicationException;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;

public class Verify extends Keywords{

    private static Logger log=Logger.getLogger(Verify.class);

    public void elementIsPresent(String locatorKey) throws ApplicationException {
        log.info("Verify element ["+locatorKey+"] is present");
        get.elementBy(locatorKey);
        log.info("Element is present!");
    }

    public void elementIsPresent(By locator) throws ApplicationException {
        log.info("Verify element ["+locator+"] is present");
        get.elementBy(locator);
        log.info("Element is present!");
    }

    public void elementTextMatching(String locatorKey,String expectedValue) throws ApplicationException {
        log.info("Verify element ["+locatorKey+"] text is matching with ["+expectedValue+"]");
        String actualValue=Test.tools.REMOVE_MULTIPLE_SPACES_AND_NEW_LINES(get.elementBy(locatorKey).getText().trim());
        try{
            isMatching(expectedValue,actualValue);
        }catch (Exception ex){
            log.error(ex);
            throw new ApplicationException(ex.getMessage());
        }
        log.info("Condition verified!");
    }

    public void elementTextContains(String locatorKey,String expectedValue) throws ApplicationException {
        log.info("Verify element ["+locatorKey+"] text is matching with ["+expectedValue+"]");
        String actualValue=Test.tools.REMOVE_MULTIPLE_SPACES_AND_NEW_LINES(get.elementBy(locatorKey).getText().trim());
        try{
            Assert.assertTrue(actualValue.contains(expectedValue.trim()));
        }catch (AssertionError ex){
            log.error("Actual value ["+actualValue+"] not matching with the Expected value["+expectedValue+"]");
            throw new ApplicationException("Actual value ["+actualValue+"] not matching with the Expected value["+expectedValue+"]");
        }
        log.info("Condition verified!");
    }

    public void elementTextMatching(By locator,String expectedValue) throws ApplicationException {
        log.info("Verify element ["+locator+"] text is matching with ["+expectedValue+"]");
        String actualValue=Test.tools.REMOVE_MULTIPLE_SPACES_AND_NEW_LINES(get.elementBy(locator).getText().trim());
        try{
            isMatching(expectedValue,actualValue);
        }catch (Exception ex){
            log.error(ex);
            throw new ApplicationException(ex.getMessage());
        }
        log.info("Condition verified!");
    }

    public void elementTextContains(By locator,String expectedValue) throws ApplicationException {
        log.info("Verify element ["+locator+"] text is matching with ["+expectedValue+"]");
        String actualValue=Test.tools.nbspRemove(get.elementBy(locator).getText().trim());
        try{
            Assert.assertTrue(actualValue.contains(expectedValue.trim()));
        }catch (AssertionError ex){
            log.error("Actual value ["+actualValue+"] don't contain the Expected value["+expectedValue+"]");
            throw new ApplicationException("Actual value ["+actualValue+"] don't contain the Expected value["+expectedValue+"]");
        }
        log.info("Condition verified!");
    }

    public void isMatching(String expected,String actual) throws ApplicationException {
        try{
            Assert.assertEquals(Test.tools.nbspRemove(actual).toLowerCase(),expected.trim().toLowerCase());
        }catch (AssertionError ex){
            log.info(ex);
            throw new ApplicationException(ex.getMessage());
        }
        log.info("Expected value ["+expected+"] is matching with the actual ["+actual+"]");
    }

    public void isMatching(double expected,double actual) throws ApplicationException {
        try{
            Assert.assertEquals(actual,expected);
        }catch (AssertionError ex){
            log.info(ex);
            throw new ApplicationException(ex.getMessage());
        }
        log.info("Expected value ["+expected+"] is matching with the actual ["+actual+"]");
    }

    public void elementAttributeMatching(String locatorKey, String whichAttribute,String expectedValue) throws ApplicationException {
        log.info("Verify if the element ["+locatorKey+"] attribute ["+whichAttribute+"] is matching with the value ["+expectedValue+"]");
        try{
            Assert.assertEquals(get.elementAttribute(locatorKey,whichAttribute),expectedValue.trim());
        }catch (AssertionError ex){
            log.error(ex);
            throw new ApplicationException(ex.getMessage());
        }
    }

    public void elementAttributeMatching(By locator, String whichAttribute,String expectedValue) throws ApplicationException {
        log.info("Verify if the element ["+locator+"] attribute ["+whichAttribute+"] is matching with the value ["+expectedValue+"]");
        try{
            Assert.assertEquals(get.elementAttribute(locator,whichAttribute),expectedValue.trim());
        }catch (AssertionError ex){
            log.error(ex);
            throw new ApplicationException(ex.getMessage());
        }
    }
}