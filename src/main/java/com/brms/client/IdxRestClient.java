package com.brms.client;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.drools.core.runtime.impl.ExecutionResultImpl;
import org.kie.api.KieServices;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNDecisionResult;
import org.kie.dmn.api.core.DMNResult;
import org.kie.internal.runtime.helper.BatchExecutionHelper;
import org.kie.server.api.marshalling.Marshaller;
import org.kie.server.api.marshalling.MarshallerFactory;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.DMNServicesClient;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;

import com.bell.belldemo.BellApp;
import com.idexx.idexxdemo.ValidationDomain;



public class IdxRestClient {
	
	
	public static void main(String[] s){
		
		IdxRestClient el = new IdxRestClient();
		el.execute();
	        
	}
	public void execute(){
		String serverUrl = "http://localhost:8080/kie-server/services/rest/server";
	    String user = "rhdmAdmin";
	     
	       String password = "jboss123$";
	       String containerId = "idexxdemo";
	        KieServicesClient  kieServicesClient = IdxRestClient.configure(serverUrl, user, password);

		 RuleServicesClient ruleClient = kieServicesClient.getServicesClient(RuleServicesClient.class);
	        KieCommands commandsFactory =
	         		KieServices.Factory.get().getCommands();
	       
	        
	        ValidationDomain valid = new ValidationDomain();
	        valid.setAssayCode("CREA");
	       // valid.setAssayCode("SDMA");
	        valid.setAnimalGroup("CANINE");
	        valid.setValue(44.0);
	        
	      //Command<?> startEmp =  commandsFactory.newStartProcess("belldemo.techservice", param);
	        Command<?> insertEmp = commandsFactory.newInsert(valid, "output");
	        Command<?> dispose  = commandsFactory.newDispose();
	        Command<?> fireAllRules = commandsFactory.newFireAllRules();
	        Command<?> batchCommand =
	      			 commandsFactory.newBatchExecution(Arrays.asList(insertEmp,fireAllRules,dispose));
	        
	       // String xStreamXml = BatchExecutionHelper.newXStreamMarshaller().toXML(batchCommand); // actual XML request
	       // String xStreamXml = BatchExecutionHelper.newJSonMarshaller().toXML(batchCommand);
	       
	        Marshaller marshaller = MarshallerFactory.getMarshaller( MarshallingFormat.JSON, getClass().getClassLoader() );
	        String xStreamXml = marshaller.marshall( batchCommand );
	        System.out.println("\t######### Rules request"  + xStreamXml);
	   
	        ServiceResponse<String> result = ruleClient.executeCommands(containerId, batchCommand);
	     
	        String resut = result.getResult();
	        System.out.println("\t######### Rules executed" + result.toString());

	      // ExecutionResultImpl results = marshaller.unmarshall(resut, ExecutionResultImpl.class);
	       //System.out.println("\t######### Rules executed" + results.toString());
	       
	}
	/*public void payload(){
		String serverUrl = "http://localhost:8080/kie-execution-server/services/rest/server";
	    String user = "rhdmAdmin";
	     
	       String password = "jboss123$";
	       String containerId = "dm7test";
	       KieServicesClient  kieServicesClient = Eligibility.configure(serverUrl, user, password);
	        RuleServicesClient ruleClient = kieServicesClient.getServicesClient(RuleServicesClient.class);
	        KieCommands commandsFactory =
	         		KieServices.Factory.get().getCommands();
	        EligibilityCriteria eligiblity = new EligibilityCriteria();
	        eligiblity.setCritrria("BMI");
	        eligiblity.setValue(151);
	        Command<?> insertEmp = commandsFactory.newInsert(eligiblity, "eligibilityResponse");
	        
	        Command<?> fireAllRules = commandsFactory.newFireAllRules();
	        Command<?> batchCommand =
	      			 commandsFactory.newBatchExecution(Arrays.asList(insertEmp,fireAllRules));
	        Marshaller marshaller = MarshallerFactory.getMarshaller( MarshallingFormat.JSON, getClass().getClassLoader() );
	        String result = marshaller.marshall( batchCommand );
	        //String result = BatchExecutionHelper.newXStreamMarshaller().toXML(batchCommand);
	      //  String result = BatchExecutionHelper.newJSonMarshaller().toXML(batchCommand);
	        		//newJSonMarshaller().toXML(batchCommand);

        
	}*/
public static KieServicesClient configure(String url, String username, String password) {
		
		//default marshalling format is JAXB
		KieServicesConfiguration config = KieServicesFactory.newRestConfiguration(url, username, password);
		
	
		//config.setMarshallingFormat(MarshallingFormat.XSTREAM);
		config.setMarshallingFormat(MarshallingFormat.JSON);

	
		
		return KieServicesFactory.newKieServicesClient(config);
		//
	}
}
