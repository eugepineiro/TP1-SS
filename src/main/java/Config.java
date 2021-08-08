/*
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
*/

import java.io.FileReader;
import java.util.List;

public class Config {

    private Integer M = 5;
    private Integer N = 5;
    private Integer rc = 5;
    private List<Particle> particles;


    /*
    private parse(){
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("../resources/config/config.json"));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) obj;

            // A JSON array. JSONObject supports java.util.List interface.
            JSONArray companyList = (JSONArray) jsonObject.get("M");
        }
    }
    */

}