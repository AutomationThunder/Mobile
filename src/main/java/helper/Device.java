package helper;

import base.Test;
import constants.Keys;
import constants.OS;

public class Device {

    public static boolean isAndroid(){
        return Test.attributes.get(Keys.OS).equalsIgnoreCase(OS.ANDROID);
    }

    public static boolean isIOS(){
        return Test.attributes.get(Keys.OS).equalsIgnoreCase(OS.iOS);
    }
}
