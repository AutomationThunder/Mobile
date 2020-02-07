package stepdefinitions;


import base.Keywords;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;

public class StepsBase{
	
	private static Logger log=Logger.getLogger(StepsBase.class);
	
	@Before
    public void beforeScenario(Scenario scenario){
        log.info("Executing S C E N A R I O : " + scenario.getName());
    }
	
	@After(order=1)
    public void ifScenarioFailed(Scenario scenario){
	    if(scenario.isFailed())
	    {
	    	log.error("S C E N A R I O    F A I L E D !!!!!");
	    	if(Keywords.driver!=null)
	    	{
				Keywords.screenshot.attachScreenshot("ScenarioFailed");
	    	}
	    }
    }

    @After(order=0)
    public void afterScenario()
	{
		Keywords.closeApplication();
	}
}
