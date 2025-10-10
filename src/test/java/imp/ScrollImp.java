package imp;

import com.thoughtworks.gauge.Step;
import helper.ScrollHelper;

public class ScrollImp extends ScrollHelper {


    @Step("<elementName> elementine scroll et")
    public void elementineScrollEt(String elementName) {
        this.scrollToElement(elementName);
    }

    @Step("<elementName> elementine scroll et ve klik et")
    public void elementineScrollEtVeKlikEt(String elementName) {
        this.scrollToElelemntAndClick(elementName);
    }


    @Step("Sehifenin yuxarısına scroll et")
    public void  sehifeninYuxarisinaScrollEt() {
        this.scrollToTop();
    }


    @Step("Sehifenin aşağısına scroll et")
    public void  sehifeninAsagisinaScrollEt() {
        this.scrollToBottom();
    }


    @Step("<pixels> piksel horizontal scroll et")
    public void  pikselHorizontalScrollEt(int pixels) {
        this.scrollHorizontallyByPixels(pixels);
    }



}
