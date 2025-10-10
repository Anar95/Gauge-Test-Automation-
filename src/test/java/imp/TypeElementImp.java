package imp;

import com.thoughtworks.gauge.Step;
import helper.TypeHelper;


public class TypeElementImp extends TypeHelper {
    @Step("<elementName> elementine <text> yaz ve Enter düymesine bas")
    public void typeAndEnter(String elementName, String text){
        this.typeAndPressKey(elementName,text, "ENTER");
    }




@Step("<elementName> elementine <text> yaz ve Tab düymesine bas")
public  void  typeAndTab(String elementName, String text){
    this.typeAndPressKey(elementName,text, "TAB");






    }
}