package DI;

import Services.ApiService;
import Services.ExtendApiService;

public class DI {
    private static ApiService service= new ExtendApiService();
    public static ApiService getService() {return service;}
    public static ApiService getNewService (){
        return new ExtendApiService();
    }
}
