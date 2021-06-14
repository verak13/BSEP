package admin.admin.services;

import java.io.*;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import admin.admin.keystore.KeyStoreReader;
import javassist.bytecode.ByteArray;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import admin.admin.dto.LogConfigDTO;
import admin.admin.model.LogConfig;
import admin.admin.repository.HospitalRepository;
import org.springframework.web.client.RestTemplate;


@Service
public class LogConfigService {
	
	@Autowired
	HospitalRepository hospitalRepository;

	@Autowired
	KeyStoreReader keyStoreReaderService;

	@Autowired
	private RestTemplate appRestClient;

	public boolean createConfig(@Valid LogConfigDTO logConfigDTO) throws IOException {

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

				String[] splitted = line.split("\\\\");
				String logFile = splitted[splitted.length-1];
				logFile = logFile.substring(0, logFile.length()-4);

				if (logFile.equalsIgnoreCase(logConfigDTO.getFile())) {
					path = line.toString();
					break;
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
		byte[] configSerialized = new byte[0];

		try(ByteArrayOutputStream b = new ByteArrayOutputStream()){
			try(ObjectOutputStream o = new ObjectOutputStream(b)){
				o.writeObject(logConfig);
			}
			configSerialized = b.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}

		PrivateKey pk = keyStoreReaderService.readPrivateKey("root-ca");
		byte[] signature = keyStoreReaderService.sign(configSerialized, pk);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
		outputStream.write(signature);
		outputStream.write(configSerialized);
		byte dataToSend[] = outputStream.toByteArray();



		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/octet-stream");
        HttpEntity<byte[]> request = new HttpEntity<>(dataToSend, headers);

		System.out.println("DATA SENDUJEM " + request.getBody().length);

		ResponseEntity<?> response = appRestClient.exchange("https://localhost:8442//log-config",
                HttpMethod.POST, request, String.class);

        System.out.println(" JE ODG GG  " + response.getStatusCode());

        return response.getStatusCode() == HttpStatus.CREATED;
	}

}
