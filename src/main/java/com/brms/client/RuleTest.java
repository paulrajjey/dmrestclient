package com.brms.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

//import org.dmg.pmml.pmml_4_2.descr.Scorecard;
import org.drools.core.ClassObjectFilter;
import org.drools.core.marshalling.impl.ProtobufMessages.FactHandle;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.ReleaseId;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.AgendaGroupPoppedEvent;
import org.kie.api.event.rule.AgendaGroupPushedEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.event.rule.RuleFlowGroupActivatedEvent;
import org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import com.idexx.idexxdemo.ValidationDomain;



public class RuleTest {
    
    public KieContainer createContainer() {
    	System.out.println("kie container creation...");
    	KieServices ks = KieServices.Factory.get();
    	//ReleaseId rId = ks.newReleaseId("citi", "cbol", "1.1");
    	//ReleaseId rId = ks.newReleaseId("redhatcentral", "demo1", "1.3");
    	ReleaseId rId = ks.newReleaseId("redhat", "appointment", "1.0");
    	//ReleaseId rId = ks.newReleaseId("test", "Test", "1.4") ;
    	KieContainer kContainer = ks.newKieContainer(rId);
    	KieScanner scanner =  ks.newKieScanner(kContainer);
    	scanner.start(60000);
    	return kContainer;
    }
	public static void main(String[] args){
		
		RuleTest obj  = new RuleTest();
		
		//List<HashMap<String, String>> result = obj.balancesDisplayedForProductMap("HKGCB", "742", "1", "0");
		//obj.eventTest1();
		obj.eventTest3();
		//System.out.println(result);
	}
public void eventTest3(){
	KieServices ks = KieServices.Factory.get();
	//ReleaseId rId = ks.newReleaseId("citi", "cbol", "1.1");
	//ReleaseId rId = ks.newReleaseId("redhatcentral", "demo1", "1.3");
	ReleaseId rId = ks.newReleaseId("employeerostering", "employeerostering", "1.0.6");
	//ReleaseId rId = ks.newReleaseId("test", "Test", "1.4") ;
	KieContainer kContainer = ks.newKieContainer(rId);
		KieSession kSession = kContainer.newKieSession("");
		KieRuntimeLogger logger;
    	logger = ks.getLoggers().newFileLogger(kSession, "/Users/JeyPaulraj/ruleaudit2");
		kSession.addEventListener(new AgendaEventListener() {
			
			public void matchCreated(MatchCreatedEvent event) {
				System.out.println("Rule name  ::" + event.getMatch().getRule().getName());
				
			}
			
			
			public void matchCancelled(MatchCancelledEvent event) {
				// TODO Auto-generated method stub
				
			}
			
			
			public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
				// TODO Auto-generated method stub
				
			}
			
			public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
				// TODO Auto-generated method stub
				
			}
			
			public void beforeMatchFired(BeforeMatchFiredEvent event) {
				// TODO Auto-generated method stub
				
			}
			
			
			public void agendaGroupPushed(AgendaGroupPushedEvent event) {
				// TODO Auto-generated method stub
				
			}
			
			public void agendaGroupPopped(AgendaGroupPoppedEvent event) {
				// TODO Auto-generated method stub
				
			}
			
			public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
				// TODO Auto-generated method stub
				
			}
			
			public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
				// TODO Auto-generated method stub
				
			}
			
			public void afterMatchFired(AfterMatchFiredEvent event) {
				System.out.println("afterMatchFired   ::" + event.getMatch().getRule().getName());
				
			}
		});
		
		
}
	
}
