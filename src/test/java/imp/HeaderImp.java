package imp;

import com.thoughtworks.gauge.Step;
import helper.ApiHelper;

public class HeaderImp {


    @Step("Add as a header <key> = <value>")
    public void addHeaderToRequest(String key, String value){
        ApiHelper.getInstance().getRequestSpecification().header(key,value);

    }

    @Step("Add Bearer token <token>")
    public void addBearerTokenToRequest(String token){
        ApiHelper.getInstance().getRequestSpecification()
                .header("Bearer",token);
    }
}
