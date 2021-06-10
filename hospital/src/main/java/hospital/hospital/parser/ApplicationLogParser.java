package hospital.hospital.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import hospital.hospital.enums.LogSeverity;
import hospital.hospital.model.Log;


public class ApplicationLogParser {

	public Log parse(String log) {
		String[] splitByThread = log.split("] ");
		String secondPart = String.join("] ", Arrays.asList(splitByThread).subList(1, Arrays.asList(splitByThread).size()));
		String[] tokens1 = splitByThread[0].split(" ");
		String[] tokens2 = secondPart.split(" ");

		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tokens1[0] + " " + tokens1[1]);
		} catch (ParseException e) {
			return null;
		}
		String ip = "";
		String username = "";
		String type = "APPLICATION";
		String severity = tokens2[0];
		String source = tokens2[2];
		String message = String.join(" ", Arrays.asList(tokens2).subList(4, Arrays.asList(tokens2).size()));

		System.out.println(new Log(null, date, source, type, LogSeverity.valueOf(severity), ip, message, username));
		return new Log(null, date, source, type, LogSeverity.valueOf(severity), ip, message, username);
	}
	
	/*
	 public Log parse(String log) {
		String[] tokens = log.split(" ");

		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tokens[0] + " " + tokens[1]);
		} catch (ParseException e) {
			return null;
		}
		String source = tokens[4];
		String ip = "";
		String username = "";
		String type = "";
		String severity = tokens[3];
		String message = String.join(" ", Arrays.asList(tokens).subList(6, Arrays.asList(tokens).size()));

		return new Log(null, date, source, type, LogSeverity.valueOf(severity), ip, message, username);
	}
	*/
	

}
