package base;

import constants.Keys;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class RunManager
{

    private File executionDir=new File("emailable-results");
    private File runHistory=new File("src/test/resources/settings/RunCount.properties");
    private String runID;

    public RunManager() {
        createRunFolder();
        Logger log=Logger.getLogger(RunManager.class);
        log.info("Hey! "+System.getProperty("user.name")+", Logging everything from here ....");
        log.info("Run ID is --> "+runID);
    }

    private void createRunFolder() {
        boolean dirReady=true;
        if(!executionDir.exists())
        {
            if(!executionDir.mkdir())
            {
                dirReady=false;
                System.out.println("Problem while creating the \"emailable-results\" directory!");
            }
        }
        if(dirReady)
        {
            runID=getRunID();
            Test.attributes.put(Keys.RunID,runID);
            File runDir=new File(executionDir.getPath()+"/"+runID);
            if(runDir.mkdir())
            {
                setRunFolder(runDir.getPath());
            }
        }
    }

    private void setRunFolder(String runFolder) {
        System.setProperty("runFolder",runFolder+"/");
        Test.attributes.put(Keys.RunFolder,runFolder+"/");
    }

    private String getRunID() {
        Properties prop=new Properties();
        FileInputStream in;
        FileOutputStream out;
        String id=null;
        boolean gotRunID=false;
        try {
            in=new FileInputStream(runHistory);
            prop.load(in);
            out=new FileOutputStream(runHistory,true);
            int runID=Integer.parseInt(prop.getProperty("Run").trim())+1;
            prop.replace("Run",Integer.toString(runID));
            prop.store(out,"lastRunID updated");
            out.close();
            id=Integer.toString(runID);
            if(!(runID==0))
            {
                gotRunID=true;
            }
        }catch(Throwable e) {
            gotRunID=false;
        }
        if(!gotRunID) {
            System.out.println("Problem while fetching the Run ID, proceeding the test with system generated Run ID");
            SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
            id="Auto-"+dateFormat.format(new Date());
        }
        return "Run-"+id;
    }
}