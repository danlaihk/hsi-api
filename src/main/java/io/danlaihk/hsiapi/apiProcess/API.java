package io.danlaihk.hsiapi.apiProcess;

import io.danlaihk.hsiapi.Underlying;
import io.danlaihk.hsiapi.json.Constituent;
import io.danlaihk.hsiapi.json.Jdata;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class API {
    public API(){}
    public Map<String, Underlying> getJdata(RestTemplate rTemp){


        Map<String, Underlying> uMap = new HashMap<>();
        try{
            String[] langArr = {"chi", "eng"};


            for(String lang: langArr){
                //https://www.hsi.com.hk/data/eng/rt/index-series/hsi/constituents.do

                String url = "https://www.hsi.com.hk/data/"+lang+"/rt/index-series/hsi/constituents.do";

                Jdata q = rTemp.getForObject(url, Jdata.class);

                //System.out.println("The api uri is:"); //
                //System.out.println(url); //
                //stime
                //System.out.println(q.getRequestDate()); //



                List<Constituent> list = q.getIndexSeriesList().get(0).getIndexList().get(0).getConstituentContent();

                for(Constituent con: list){
                    String key = con.getCode();
                    if(!uMap.containsKey(key)){
                        Underlying underlying = new Underlying(con.getCode());


                        if(lang.equals("chi")){

                            underlying.setCname(con.getConstituentName());
                        }else if(lang.equals("eng")){
                            underlying.setName(con.getConstituentName());
                        }

                        uMap.put(key, underlying);
                    }else {
                        Underlying underlying = uMap.get(key);


                        if(lang.equals("chi")){

                            underlying.setCname(con.getConstituentName());
                        }else if(lang.equals("eng")){
                            underlying.setName(con.getConstituentName());
                        }
                    }



                }




            }


        }catch (Exception e){
            System.out.println(e.toString());
        }
        return uMap;
    }

}
