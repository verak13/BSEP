package hospital.hospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import hospital.hospital.model.LogConfig;
import hospital.hospital.model.LogConfigs;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LogConfigService {
	
	private static final Logger logger = LoggerFactory.getLogger(LogConfigService.class);
	
	@Autowired
	private LogService logService;

	public void saveConfig(LogConfig logConfig) throws JsonIOException, IOException {
		Gson gson = new GsonBuilder().create();
		File file = ResourceUtils.getFile("classpath:configuration.json");
		
		LogConfigs configs = gson.fromJson(new FileReader(file), LogConfigs.class);
		
		boolean found = false;
		for (LogConfig conf : configs.getLogConfigs()) {
			if (conf.getFile().equals(logConfig.getFile()) && conf.getHospitalId().equals(logConfig.getHospitalId())) {
				found = true;
				conf.setInterval(logConfig.getInterval());
				conf.setRegexp(logConfig.getRegexp());
			}
		}
		if (!found) {
			configs.getLogConfigs().add(logConfig);
	        this.logService.addNewConfig(logConfig);
		}
		
		
		Writer writer = new FileWriter(file.getAbsolutePath());
		gson.toJson(configs, writer);
        writer.close();
        
        logger.info("New log configuration saved to log config file.");
        
	}

}
