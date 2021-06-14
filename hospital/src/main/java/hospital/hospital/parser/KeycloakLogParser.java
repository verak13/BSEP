package hospital.hospital.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import hospital.hospital.enums.LogSeverity;
import hospital.hospital.model.Log;

public class KeycloakLogParser {
	
	public Log parse(String log) {
		String[] tokens = log.split(" ");
		if (tokens.length < 2) return null;

		Date date = null;
		try {
			String toParse = tokens[0] + " " + tokens[1].split(",")[0];
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(toParse);
		} catch (ParseException e) {
			return null;
		}
		String source = tokens[4].substring(1, tokens[4].length() - 1);
		String ip = "";
		String username = "";
		String type = "";

		if (tokens[7].split("=")[0].equals("type")){
			type = tokens[7].split("=")[1].replace(",", "");
		} else {
			int typeIndex = log.indexOf("type");
			if (typeIndex != -1) {
				String subStr = log.substring(typeIndex);
				type = subStr.split("=")[1].split(",")[0];
			}
		}
		int usernameIndex = log.indexOf("username");
		if (usernameIndex != -1) {
			String subStr = log.substring(usernameIndex);
			username = subStr.split("=")[1].split(",")[0];
		}
		String severity = tokens[2];
		int messageIndex = log.indexOf("message");
		String message = "";
		if (messageIndex != -1) {
			String subStr = log.substring(usernameIndex);
			message = subStr.split("=")[1].split(",")[0];
		}

		if (message.contains("isAddress=")) {
			ip = message.substring(message.indexOf("ipAddress=")+10, message.indexOf(",", message.indexOf("ipAddress=")));
		}

		return new Log(null, date, source, type, LogSeverity.valueOf(severity), ip, message, username);
	}

}
