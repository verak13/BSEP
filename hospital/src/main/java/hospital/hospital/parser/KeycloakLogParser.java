package hospital.hospital.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import hospital.hospital.enums.LogSeverity;
import hospital.hospital.model.Log;

public class KeycloakLogParser {
	
	public Log parse(String log) {
		System.out.println("KEYCLOAK LINE");
		System.out.println(log);
		String[] tokens = log.split(" ");

		Date date = null;
		try {
			System.out.println(tokens[0] + " " + tokens[1]);
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tokens[0] + " " + tokens[1].split(",")[0]);
			System.out.println(date.toString());
		} catch (ParseException e) {
			return null;
		}
		String source = tokens[4].substring(1, tokens[4].length() - 1);
		String ip = "";
		String username = "";
		String type = "";
		if (tokens[5].split("=")[0].equals("type")){
			type = tokens[5].split("=")[1];
		} else {
			type = "KEYCLOAK";
		}
		String severity = tokens[2];
		String message = String.join(" ", Arrays.asList(tokens).subList(5, Arrays.asList(tokens).size()));
		if (message.contains("isAddress=")) {
			ip = message.substring(message.indexOf("ipAddress=")+10, message.indexOf(",", message.indexOf("ipAddress=")));
		}

		return new Log(null, date, source, type, LogSeverity.valueOf(severity), ip, message, username);
	}

}
