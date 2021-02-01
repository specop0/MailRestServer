package routes;

import models.MailService;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class Routes {

    public static void EstablishRoutes(MailService mailService, int port) {
        spark.Spark.ipAddress("localhost");
        spark.Spark.port(port);
        spark.Spark.post("/mail/send", "application/json", (Request rqst, Response rspns) -> {
            JSONObject jsonObject = new JSONObject(rqst.body());
            String subject = jsonObject.getString("subject");
            String content = jsonObject.getString("content");

            if (subject == null || "".equals(subject)) {
                throw new IllegalArgumentException("subject null or empty.");
            }
            if (content == null || "".equals(content)) {
                throw new IllegalArgumentException("content null or empty.");
            }

            mailService.SendMessage(subject, content);

            return new JSONObject();
        });
    }
}
