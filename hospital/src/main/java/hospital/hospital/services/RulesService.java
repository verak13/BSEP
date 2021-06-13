package hospital.hospital.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.drools.template.ObjectDataCompiler;
import org.springframework.stereotype.Service;

import hospital.hospital.dto.CustomMessageRuleDTO;
import hospital.hospital.dto.CustomMessageRuleDTODouble;
import hospital.hospital.dto.RuleBloodPressureDTO;
import hospital.hospital.dto.RuleDTO;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;

@Service
public class RulesService {
	
	public boolean createHighTemperatureRule(RuleDTO dto) {
		try {
            InputStream template = new FileInputStream(
                    "src\\main\\resources\\rules\\high-temperature.drt");
            List<RuleDTO> arguments = new ArrayList<>();
            arguments.add(dto);
            ObjectDataCompiler compiler = new ObjectDataCompiler();
            System.out.println(arguments);
            System.out.println(template);
            String drl = compiler.compile(arguments, template);
            FileOutputStream drlFile = new FileOutputStream(new File(
            		"src\\main\\resources\\rules\\high-temperature" + dto.getPatient() + ".drl"), false);
            drlFile.write(drl.getBytes());
            drlFile.close();
            InvocationRequest request = new DefaultInvocationRequest();
            //request.setInputStream(InputStream.nullInputStream());
            request.setPomFile(new File("../hospital/pom.xml"));
            request.setGoals(Arrays.asList("clean", "install"));

            Invoker invoker = new DefaultInvoker();
            invoker.setMavenHome(new File(System.getenv("M2_HOME")));
            invoker.execute(request);
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
	}
	
	
	public boolean createBloodPressureRule(RuleBloodPressureDTO dto) {
		try {
            InputStream template = new FileInputStream(
                    "src\\main\\resources\\rules\\blood-pressure.drt");

            List<RuleBloodPressureDTO> arguments = new ArrayList<>();
            arguments.add(new RuleBloodPressureDTO((int)dto.getPatient(), dto.getDiastolic(), dto.getSystolic()));
            ObjectDataCompiler compiler = new ObjectDataCompiler();
            String drl = compiler.compile(arguments, template);

            FileOutputStream drlFile = new FileOutputStream(new File(
            		"src\\main\\resources\\rules\\blood-pressure" + dto.getPatient() + ".drl"), false);
            drlFile.write(drl.getBytes());
            drlFile.close();

            InvocationRequest request = new DefaultInvocationRequest();
            //request.setInputStream(InputStream.nullInputStream());
            request.setPomFile(new File("../hospital/pom.xml"));
            request.setGoals(Arrays.asList("clean", "install"));

            Invoker invoker = new DefaultInvoker();
            invoker.setMavenHome(new File(System.getenv("M2_HOME")));
            invoker.execute(request);
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
	}
	
	
	public boolean createCustomMessageRule(CustomMessageRuleDTO dto) {
		try {
			if (dto.getMinDiastolic().equals("") && dto.getMaxDiastolic().equals("")) {
				dto.setMinDiastolic("1000.0");
				dto.setMaxDiastolic("1.0");
			}
			if (dto.getMinSystolic().equals("") && dto.getMaxSystolic().equals("")) {
				dto.setMinSystolic("1000.0");
				dto.setMaxSystolic("1.0");
			}
			if (dto.getMinTemperature().equals("") && dto.getMaxTemperature().equals("")) {
				dto.setMinTemperature("1000.0");
				dto.setMaxTemperature("1.0");
			}
			if (dto.getMinPulse().equals("") && dto.getMaxPulse().equals("")) {
				dto.setMinPulse("1000.0");
				dto.setMaxPulse("1.0");
			}
			if (dto.getMinRespiration().equals("") && dto.getMaxRespiration().equals("")) {
				dto.setMinRespiration("1000.0");
				dto.setMaxRespiration("1.0");
			}
			if (dto.getMinDiastolic().equals("")) {
				dto.setMinDiastolic("50.0");
			}
			if (dto.getMinSystolic().equals("")) {
				dto.setMinSystolic("110.0");
			}
			if (dto.getMinTemperature().equals("")) {
				dto.setMinTemperature("34.0");
			}
			if (dto.getMinPulse().equals("")) {
				dto.setMinPulse("40.0");
			}
			if (dto.getMinPulse().equals("")) {
				dto.setMinPulse("40.0");
			}
			if (dto.getMaxSystolic().equals("")) {
				dto.setMaxSystolic("130.0");
			}
			if (dto.getMaxDiastolic().equals("")) {
				dto.setMaxDiastolic("90.0");
			}
			if (dto.getMaxPulse().equals("")) {
				dto.setMaxPulse("100.0");
			}
			if (dto.getMaxTemperature().equals("")) {
				dto.setMaxTemperature("40.0");
			}
			if (dto.getMaxRespiration().equals("")) {
				dto.setMaxRespiration("17.0");
			}
			
            InputStream template = new FileInputStream(
                    "src\\main\\resources\\rules\\custom-mesage-rule.drt");

            List<CustomMessageRuleDTODouble> arguments = new ArrayList<>();
            arguments.add(new CustomMessageRuleDTODouble(dto));
            ObjectDataCompiler compiler = new ObjectDataCompiler();
            String drl = compiler.compile(arguments, template);

            FileOutputStream drlFile = new FileOutputStream(new File(
            		"src\\main\\resources\\rules\\custom-mesage-rule" + UUID.randomUUID().toString() + ".drl"), false);
            drlFile.write(drl.getBytes());
            drlFile.close();

            InvocationRequest request = new DefaultInvocationRequest();
            //request.setInputStream(InputStream.nullInputStream());
            request.setPomFile(new File("../hospital/pom.xml"));
            request.setGoals(Arrays.asList("clean", "install"));

            Invoker invoker = new DefaultInvoker();
            invoker.setMavenHome(new File(System.getenv("M2_HOME")));
            invoker.execute(request);
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
	}

}
