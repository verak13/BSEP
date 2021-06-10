package hospital.hospital.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import hospital.hospital.enums.LogSeverity;
import hospital.hospital.model.Log;



public class SimulatorLogParser {
	
	public Log parse(String log) {
		String[] tokens = log.split(" ");
		//yyyy-MM-dd HH:mm:ss source type severity ip username message1 message2...
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tokens[0] + " " + tokens[1]);
		} catch (ParseException e) {
			return null;
		}
		String source = tokens[2];
		String type = tokens[3];
		String severity = tokens[4];
		String ip = tokens[5];
		String username = tokens[6];
		String message = String.join(" ", Arrays.asList(tokens).subList(7, Arrays.asList(tokens).size()));

		return new Log(null, date, source, type, LogSeverity.valueOf(severity), ip, message, username);
	}

}
