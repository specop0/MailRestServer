package main;

import java.io.IOException;
import models.File;
import models.MailService;
import routes.Routes;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            JSONObject configuration = new JSONObject(File.ReadAllText(args[0]));

            JSONObject mailConfiguration = configuration.getJSONObject("mail");
            String host = mailConfiguration.getString("host");
            String port = mailConfiguration.getString("port");
            String email = mailConfiguration.getString("email");
            String username = mailConfiguration.getString("username");
            String password = mailConfiguration.getString("password");

            JSONObject serverConfiguration = configuration.getJSONObject("server");
            int serverPort = serverConfiguration.getInt("port");

            MailService mailService = new MailService(host, port, email, username, password);

            Routes.EstablishRoutes(mailService, serverPort);
            System.out.println(String.format("Endpoint listening at: localhost:%d", serverPort));

            System.in.read();
            spark.Spark.stop();
            spark.Spark.awaitStop();
        } else {
            throw new IllegalArgumentException("No configuration file provided");
        }
    }
}
