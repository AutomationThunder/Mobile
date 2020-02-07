package xpath;

import base.Test;
import constants.Keys;
import constants.OS;

public class Contains {

    private String text;

    public Contains(String text){
        setText(text.trim());
    }

    public String getText() {
        return text;
    }

    private void setText(String text) {
        this.text = text;
    }

    public static Contains name(String name){
        return new Contains("[contains(@name,'"+name+"')]");
    }

    public static Contains text(String text){
        return new Contains("[contains(@text,'"+text+"')]");
    }

    public static Contains youDecide(String value){
        if(Test.attributes.get(Keys.OS).toString().equalsIgnoreCase(OS.ANDROID)){
            return new Contains("[contains(@text,'"+value+"')]");
        }else{
            return new Contains("[contains(@name,'"+value+"')]");
        }
    }
}
