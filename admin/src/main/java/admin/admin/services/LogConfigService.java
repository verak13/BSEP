package admin.admin.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import admin.admin.dto.LogConfigDTO;
import admin.admin.model.LogConfig;
import admin.admin.repository.HospitalRepository;


@Service
public class LogConfigService {
	
	@Autowired
	HospitalRepository hospitalRepository;

	public boolean createConfig(@Valid LogConfigDTO logConfigDTO) {
		
		if (hospitalRepository.findById(logConfigDTO.getHospitalId()) == null) {
			return false;
		}
		
		String path = "";
		try {
			File file = ResourceUtils.getFile("classpath:simulators.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (null != line) {
				line = line.replace("\n", "");
				System.out.println(line.split(" ")[0]);
				System.out.println(line.split(" ")[1]);
				if (line.split(" ")[0].equals(logConfigDTO.getFile())) {
					path = line.split(" ")[1];
				}
				line = reader.readLine();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		if (path.equals("")) {
			return false;
		}
		String regex = logConfigDTO.getRegexp();
		if (regex.equals("")) {
			regex = ".*";
		}
		
		LogConfig logConfig = new LogConfig(path, logConfigDTO.getInterval(), regex, logConfigDTO.getHospitalId());
		
		RestTemplate restTemplate = new RestTemplate();        
        HttpEntity<LogConfig> request = new HttpEntity<>(logConfig);
        ResponseEntity<?> response = restTemplate.exchange("https://localhost:8442//log-config",
                HttpMethod.POST, request, LogConfig.class);

        return response.getStatusCode() == HttpStatus.OK;
	}

}
