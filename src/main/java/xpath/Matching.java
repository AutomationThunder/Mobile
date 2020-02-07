package xpath;

import base.Test;
import constants.Keys;
import constants.OS;

public class Matching {

    private String text;

    public Matching(String text){
        setText(text.trim());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static Matching name(String name){
        return new Matching("[@name='"+name+"']");
    }

    public static Matching text(String text){
        return new Matching("[@text='"+text+"']");
    }

    public static Matching youDecide(String value){
        if(Test.attributes.get(Keys.OS).toString().equalsIgnoreCase(OS.ANDROID)){
            return new Matching("[@text='"+value+"']");
        }else{
            return new Matching("[@name='"+value+"']");
        }
    }
}