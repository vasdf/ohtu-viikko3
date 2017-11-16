package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        
        String studentNr = "011120775";
        if (args.length>0) {
            studentNr = args[0];
        }
        
        String url = "https://studies.cs.helsinki.fi/ohtustats/students/"+studentNr+"/submissions";
        String url2 = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";
        String url3 = "https://studies.cs.helsinki.fi/ohtustats/stats";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();
        String courseinfo = Request.Get(url2).execute().returnContent().asString();
        String statsResponse = Request.Get(url3).execute().returnContent().asString();
        
        Gson mapper = new Gson();
        Kurssi course = mapper.fromJson(courseinfo, Kurssi.class);
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        
        JsonParser parser = new JsonParser();
        JsonObject parsittuData = parser.parse(statsResponse).getAsJsonObject();
        int students = parsittuData.get("1").getAsJsonObject().get("students").getAsInt();
        int exercise_total = parsittuData.get("1").getAsJsonObject().get("exercise_total").getAsInt();
        
        System.out.println("json-muotoinen data:");
        System.out.println( bodyText );
        
        System.out.println("\nKurssi: "+course.getName()+", "+course.getTerm()+"\n");
        
        System.out.println("opiskelijanumero: " + studentNr + "\n");
        int hoursSum = 0;
        int exercisesSum = 0;
        
        for (Submission submission : subs) {
            hoursSum += submission.getHours();
            exercisesSum += submission.getExercises().length;
            
            System.out.println(submission);
        }
        
        System.out.println("\nyhteensä: "+exercisesSum+" tehtävää ja aikaa kului "+hoursSum+" tuntia \n");
        
        System.out.println("kurssilla yhteensä "+students+" palautusta, palautettuja tehtäviä "+exercise_total+" kpl");
    }
}
