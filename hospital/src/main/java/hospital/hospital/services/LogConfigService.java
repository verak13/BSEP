package hospital.hospital.services;

import hospital.hospital.helper.HackedObjectInputStream;
import hospital.hospital.keystore.KeyStoreReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import hospital.hospital.model.LogConfig;
import hospital.hospital.model.LogConfigs;

import java.io.*;
import java.security.*;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LogConfigService {
	
	private static final Logger logger = LoggerFactory.getLogger(LogConfigService.class);
	
	@Autowired
	private LogService logService;

	@Autowired
	KeyStoreReader keyStoreReaderService;

	public boolean saveConfig(byte[] logConfiguration, PublicKey pk) throws JsonIOException, IOException {

		byte[] signature = Arrays.copyOfRange(logConfiguration, 0, 256);
		byte[] message = Arrays.copyOfRange(logConfiguration, 256, logConfiguration.length);

		if(!keyStoreReaderService.verifySignature(message, signature, pk)){
			return false;
		}

		LogConfig logConfig = new LogConfig();

		try(ByteArrayInputStream b = new ByteArrayInputStream(message)){
			try(HackedObjectInputStream o = new HackedObjectInputStream(b)){
				 logConfig = (LogConfig) o.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}


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
		writer.flush();
        writer.close();
        
        logger.info("New log configuration saved to log config file.");

        return true;
	}

}
