package imp;

import com.thoughtworks.gauge.Step;
import helper.VerifyHelper;

public class VerifyElementImp  extends VerifyHelper {

    @Step("Element <element> içinde <text> yazısı var")
    public void verifyElementContains(String element, String text)
    {
        this.verifyElementTextContains(element, text);
    }

    @Step("<element> görsensin deye maksimum <second> saniye gözle")
    public void waitForElement(String element, int second)
    {
            this.waitUntilElementIsVisible(element, second);
    }
}
