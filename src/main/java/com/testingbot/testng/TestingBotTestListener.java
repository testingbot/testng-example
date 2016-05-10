package com.testingbot.testng;

import com.testingbot.api.TestingbotREST;
import java.util.HashMap;
import java.util.Map;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestingBotTestListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult testResult) {
        TestingBotSessionIdProvider provider = (TestingBotSessionIdProvider) testResult.getInstance();
        String sessionId = provider.getSessionId();
        
        TestingBotAuthenticationProvider authProvider = (TestingBotAuthenticationProvider) testResult.getInstance();
        TestingbotREST testingbotRest = new TestingbotREST(authProvider.getTestingBotKey(), authProvider.getTestingBotSecret());
        
        Map<String, String> data = new HashMap<String, String>();
        data.put("success", "0");
        data.put("name", testResult.getMethod().getMethodName());

        testingbotRest.updateTest(sessionId, data);
        super.onTestFailure(testResult);
    }
    
    @Override
    public void onTestSuccess(ITestResult testResult) {
        
        TestingBotSessionIdProvider provider = (TestingBotSessionIdProvider) testResult.getInstance();
        String sessionId = provider.getSessionId();
        
        TestingBotAuthenticationProvider authProvider = (TestingBotAuthenticationProvider) testResult.getInstance();
        TestingbotREST testingbotRest = new TestingbotREST(authProvider.getTestingBotKey(), authProvider.getTestingBotSecret());
        
        Map<String, String> data = new HashMap<String, String>();
        data.put("success", "1");
        data.put("name", testResult.getMethod().getMethodName());

        testingbotRest.updateTest(sessionId, data);
        super.onTestSuccess(testResult);
    }
}
