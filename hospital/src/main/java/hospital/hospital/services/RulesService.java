package hospital.hospital.services;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import hospital.hospital.dto.*;
import hospital.hospital.model.cep.LogEvent;
import hospital.hospital.model.cep.UserLoginEvent;
import org.apache.maven.shared.invoker.*;
import org.drools.compiler.kproject.ReleaseIdImpl;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.template.DataProvider;
import org.drools.template.ObjectDataCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieRuntime;
import org.kie.api.runtime.KieSession;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hospital.hospital.dto.CustomMessageRuleDTO;
import hospital.hospital.dto.CustomMessageRuleDTODouble;
import hospital.hospital.dto.RuleBloodPressureDTO;
import hospital.hospital.dto.RuleDTO;

@Service
public class RulesService {

    @Autowired
    private KieSession kieSession;

    @Value("${pathdrools}")
    private String pathDrools;
	
	public boolean createHighTemperatureRule(RuleDTO dto) {
        String templatePath = pathDrools + "\\src\\main\\resources\\hospital.hospital\\rules\\template";
		try {
            InputStream template = new FileInputStream(templatePath + "\\high-temperature.drt");
            List<RuleDTO> arguments = new ArrayList<>();
            arguments.add(dto);
            ObjectDataCompiler compiler = new ObjectDataCompiler();
            String drl = compiler.compile(arguments, template);

            String outputPath = pathDrools + "\\src\\main\\resources\\hospital.hospital\\rules\\high-temperature" + dto.getPatient() + ".drl";
            reloadKjar(drl, outputPath);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
	}

	public boolean createBloodPressureRule(RuleBloodPressureDTO dto) {
        String templatePath = pathDrools + "\\src\\main\\resources\\hospital.hospital\\rules\\template";
		try {
            InputStream template = new FileInputStream(templatePath + "\\blood-pressure.drt");

            List<RuleBloodPressureDTO> arguments = new ArrayList<>();
            arguments.add(new RuleBloodPressureDTO((int)dto.getPatient(), dto.getDiastolic(), dto.getSystolic()));
            ObjectDataCompiler compiler = new ObjectDataCompiler();
            String drl = compiler.compile(arguments, template);

            String outputPath = pathDrools + "\\src\\main\\resources\\hospital.hospital\\rules\\blood-pressure" + dto.getPatient() + ".drl";
            reloadKjar(drl, outputPath);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
	}

	public boolean createCustomMessageRule(CustomMessageRuleDTO dto) {
        String templatePath = pathDrools + "\\src\\main\\resources\\hospital.hospital\\rules\\template";
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
			
            InputStream template = new FileInputStream(templatePath + "\\custom-mesage-rule.drt");

            List<CustomMessageRuleDTODouble> arguments = new ArrayList<>();
            arguments.add(new CustomMessageRuleDTODouble(dto));
            ObjectDataCompiler compiler = new ObjectDataCompiler();
            String drl = compiler.compile(arguments, template);

            String outputPath = pathDrools + "\\src\\main\\resources\\hospital.hospital\\rules\\custom-mesage-rule" + UUID.randomUUID().toString() + ".drl";
            reloadKjar(drl, outputPath);

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
	}

	public boolean createLogRule(LogRuleDTO dto) {
        String templatePath = pathDrools + "\\src\\main\\resources\\hospital.hospital\\rules\\template";
	    String drlPath = templatePath + "\\custom-log-rules.drt";

	    if (dto.getTimes() != 0) {
	        dto.setTimes(dto.getTimes() - 1);
        }
	    if (dto.getSeconds() == 0) {
	        dto.setSeconds(0);
        }
	    if (dto.getType().getList().size() == 0) {
	        return false;
        }
	    if (dto.getSeverity().getList() == null) {
	        ArrayList<String> list = new ArrayList<>();
	        list.add("WARN");
	        list.add("INFO");
	        list.add("ERROR");
	        list.add("TRACE");
	        dto.setSeverity(new TypeList(list));
        }
        if (dto.getPrecTypes().getList() == null && dto.getSuccTypes().getList() == null) {
            drlPath = pathDrools + "\\src\\main\\resources\\hospital.hospital\\rules\\template\\custom-log-rules-simple.drt";

        } else if(dto.getPrecTypes().getList() == null) {
            ArrayList<String> list = new ArrayList<>();
            list.add("ERROR");
            list.add("LOGIN_ERROR");
            list.add("APPLICATION");
            list.add("LOGIN");
            dto.setPrecTypes(new TypeList(list));
            dto.setPrecSec(10000);
        } else if (dto.getSuccTypes().getList() == null) {
            ArrayList<String> list = new ArrayList<>();
            list.add("ERROR");
            list.add("LOGIN_ERROR");
            list.add("APPLICATION");
            list.add("LOGIN");
            dto.setSuccTypes(new TypeList(list));
            dto.setSuccSec(10000);
        }
	    try {
            InputStream template = new FileInputStream(drlPath);
            List<LogRuleDTO> arguments = new ArrayList<>();
            arguments.add(dto);

            ObjectDataCompiler compiler = new ObjectDataCompiler();
            String drl = compiler.compile(arguments, template);

            String outputPath = pathDrools + "\\src\\main\\resources\\hospital.hospital\\rules\\custom-log-rules" + UUID.randomUUID().toString() + ".drl";

            reloadKjar(drl, outputPath);
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void reloadKjar(String drl, String outputPath) throws IOException, MavenInvocationException {
        FileOutputStream drlFile = new FileOutputStream(new File(outputPath), false);
        drlFile.write(drl.getBytes());
        drlFile.close();

        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File(pathDrools+ "\\pom.xml"));
        request.setGoals(Arrays.asList("clean", "install"));

        Invoker invoker = new DefaultInvoker();
        invoker.setMavenHome(new File(System.getenv("M2_HOME")));
        invoker.execute(request);
    }
}
