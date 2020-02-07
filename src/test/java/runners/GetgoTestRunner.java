package runners;

import base.Test;
import constants.Device;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"stepdefinitions"},
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:","pretty","json:target/json-output/Automation-Summary.json"},
        tags={"~@IOSOnly"}
)

public class GetgoTestRunner extends Test {
    @BeforeClass
    public static void initialize(){
        String deviceName=System.getProperty("Device");
        if(deviceName==null){
            deviceName=Device.TGSMOB2131;
        }
        setup(deviceName);
    }

    @AfterClass
    public static void flush(){
        teardown();
    }
}