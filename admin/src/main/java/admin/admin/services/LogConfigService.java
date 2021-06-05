package admin.admin.services;

import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import admin.admin.model.LogConfig;


@Service
public class LogConfigService {

	public boolean createConfig(@Valid LogConfig logConfig) {
		RestTemplate restTemplate = new RestTemplate();        
        HttpEntity<LogConfig> request = new HttpEntity<>(logConfig);
        ResponseEntity<?> response = restTemplate.exchange("https://localhost:8442//log-config",
                HttpMethod.POST, request, LogConfig.class);

        return response.getStatusCode() == HttpStatus.OK;
	}

}
