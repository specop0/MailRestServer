package main;

import java.io.IOException;
import models.File;
import models.MailService;
import routes.Routes;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) throws IOException {
        String configurationFilename = "/mnt/configuration.json";
        if (args.length > 0) {
            configurationFilename = args[0];
        }
        JSONObject configuration = new JSONObject(File.ReadAllText(configurationFilename));

        JSONObject mailConfiguration = configuration.getJSONObject("mail");
        String host = mailConfiguration.getString("host");
        String port = mailConfiguration.getString("port");
        String email = mailConfiguration.getString("email");
        String username = mailConfiguration.getString("username");
        String password = mailConfiguration.getString("password");

        JSONObject serverConfiguration = configuration.getJSONObject("server");
        String ipAddress = serverConfiguration.getString("ipAddress");
        int serverPort = serverConfiguration.getInt("port");

        MailService mailService = new MailService(host, port, email, username, password);

        Routes.EstablishRoutes(ipAddress, serverPort, mailService);
        System.out.println(String.format("Endpoint listening at: %s:%d", ipAddress, serverPort));

        spark.Spark.awaitStop();
    }
}
