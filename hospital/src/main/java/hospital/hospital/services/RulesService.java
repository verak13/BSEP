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
            arguments.add(new RuleDTO("1", "41"));
            ObjectDataCompiler compiler = new ObjectDataCompiler();
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
            arguments.add(new RuleBloodPressureDTO("1", "120", "80"));
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
			if (dto.getMinDiastolic().equals("")) {
				dto.setMinDiastolic("0.0");
			}
			if (dto.getMinSystolic().equals("")) {
				dto.setMinSystolic("0.0");
			}
			if (dto.getMinTemperature().equals("")) {
				dto.setMinTemperature("0.0");
			}
			if (dto.getMinPulse().equals("")) {
				dto.setMinPulse("0.0");
			}
			if (dto.getMinRespiration().equals("")) {
				dto.setMinRespiration("0.0");
			}
			if (dto.getMaxSystolic().equals("")) {
				dto.setMaxSystolic("1000.0");
			}
			if (dto.getMaxDiastolic().equals("")) {
				dto.setMaxDiastolic("1000.0");
			}
			if (dto.getMaxPulse().equals("")) {
				dto.setMaxPulse("1000.0");
			}
			if (dto.getMaxTemperature().equals("")) {
				dto.setMaxTemperature("1000.0");
			}
			if (dto.getMaxRespiration().equals("")) {
				dto.setMaxRespiration("1000.0");
			}
			
            InputStream template = new FileInputStream(
                    "src\\main\\resources\\rules\\custom-message-rule.drt");

            List<CustomMessageRuleDTO> arguments = new ArrayList<>();
            arguments.add(dto);
            ObjectDataCompiler compiler = new ObjectDataCompiler();
            String drl = compiler.compile(arguments, template);

            FileOutputStream drlFile = new FileOutputStream(new File(
            		"src\\main\\resources\\rules\\custom-rule" + UUID.randomUUID().toString() + ".drl"), false);
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
