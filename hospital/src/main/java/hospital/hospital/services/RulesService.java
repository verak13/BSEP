package hospital.hospital.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import hospital.hospital.dto.*;
import hospital.hospital.model.cep.UserLoginEvent;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.template.DataProvider;
import org.drools.template.ObjectDataCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private KieSession kieSession;
	
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

            KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
            System.out.println(drl);
            kb.add(ResourceFactory.newByteArrayResource(drl.getBytes("utf-8")), ResourceType.DRL);

            KnowledgeBuilderErrors errors = kb.getErrors();
            for (KnowledgeBuilderError error : errors) {
                System.out.println(error);
            }
            KnowledgeBase kBase =  KnowledgeBaseFactory.newKnowledgeBase();
            kBase.addKnowledgePackages(kb.getKnowledgePackages());
            kieSession = kBase.newKieSession();
            kieSession.fireAllRules();


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

            KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
            System.out.println(drl);
            kb.add(ResourceFactory.newByteArrayResource(drl.getBytes("utf-8")), ResourceType.DRL);

            KnowledgeBuilderErrors errors = kb.getErrors();
            for (KnowledgeBuilderError error : errors) {
                System.out.println(error);
            }
            KnowledgeBase kBase =  KnowledgeBaseFactory.newKnowledgeBase();
            kBase.addKnowledgePackages(kb.getKnowledgePackages());
            kieSession = kBase.newKieSession();
            kieSession.fireAllRules();

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

            KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
            System.out.println(drl);
            kb.add(ResourceFactory.newByteArrayResource(drl.getBytes("utf-8")), ResourceType.DRL);

            KnowledgeBuilderErrors errors = kb.getErrors();
            for (KnowledgeBuilderError error : errors) {
                System.out.println(error);
            }
            KnowledgeBase kBase =  KnowledgeBaseFactory.newKnowledgeBase();
            kBase.addKnowledgePackages(kb.getKnowledgePackages());
            kieSession = kBase.newKieSession();
            kieSession.fireAllRules();

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
	}

	public boolean createLogRule(LogRuleDTO dto) {
	    if (dto.getTimes() != 0) {
	        dto.setTimes(dto.getTimes() - 1);
        }
	    if (dto.getSeconds() == 0) {
	        dto.setSeconds(60);
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
        if (dto.getPrecTypes().getList() == null) {
            ArrayList<String> list = new ArrayList<>();
            list.add("ERROR");
            list.add("LOGIN_ERROR");
            list.add("APPLICATION");
            list.add("LOGIN");
            dto.setPrecTypes(new TypeList(list));
            dto.setPrecSec(10000);
        }
        if (dto.getSuccTypes().getList() == null) {
            ArrayList<String> list = new ArrayList<>();
            list.add("ERROR");
            list.add("LOGIN_ERROR");
            list.add("APPLICATION");
            list.add("LOGIN");
            dto.setSuccTypes(new TypeList(list));
            dto.setSuccSec(10000);
        }
	    try {
            InputStream template = new FileInputStream(
                    "src\\main\\resources\\rules\\custom-log-rules.drt");

            List<LogRuleDTO> arguments = new ArrayList<>();

            arguments.add(dto);

            ObjectDataCompiler compiler = new ObjectDataCompiler();
            String drl = compiler.compile(arguments, template);

            KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
            System.out.println(drl);
            kb.add(ResourceFactory.newByteArrayResource(drl.getBytes("utf-8")), ResourceType.DRL);

            KnowledgeBuilderErrors errors = kb.getErrors();
            for (KnowledgeBuilderError error : errors) {
                System.out.println(error);
            }
            KnowledgeBase kBase =  KnowledgeBaseFactory.newKnowledgeBase();
            kBase.addKnowledgePackages(kb.getKnowledgePackages());
            kieSession = kBase.newKieSession();
            kieSession.fireAllRules();

            FileOutputStream drlFile = new FileOutputStream(new File(
                    "src\\main\\resources\\rules\\custom-log-rules" + UUID.randomUUID().toString() + ".drl"), false);
            drlFile.write(drl.getBytes());
            drlFile.close();

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
