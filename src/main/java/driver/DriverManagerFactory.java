package driver;

import base.Test;
import constants.Keys;
import helper.PropertyReader;

public class DriverManagerFactory {

    public static DriverManager getManager() {

        DriverManager driverManager;

        DriverType type = DriverType.valueOf(PropertyReader.valueOf("Device."+Test.attributes.get(Keys.Device)+".OS").toUpperCase());

        switch (type) {
            case ANDROID:
                driverManager = new AndroidManager();
                break;
            case IOS:
                driverManager = new IOSManager();
                break;
            default:
                driverManager = new AndroidManager();
                break;
        }
        return driverManager;
    }
}