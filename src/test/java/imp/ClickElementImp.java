package imp;

import com.thoughtworks.gauge.Step;
import helper.ClickHelper;

public class ClickElementImp extends ClickHelper {


    @Step("Json filendakı <element> elementine klik et")
    public void elementineKlikElement(String element)
    { this.ClickElement(element);

    }
}
