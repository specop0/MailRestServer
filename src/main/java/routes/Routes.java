package routes;

import models.MailService;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class Routes {

    public static void EstablishRoutes(String ipAddress, int port, MailService mailService) {
        spark.Spark.ipAddress(ipAddress);
        spark.Spark.port(port);
        spark.Spark.get("/", (req, res) -> "MailRestServer is running");
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
