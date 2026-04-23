package org.example.framework.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    @Override
    public void onStart(ITestContext context) {
        System.out.println("\n--- 🏁 Test Suite Started: " + context.getName() + " ---");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("🚀 Starting Test: [" + result.getMethod().getMethodName() + "]");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ Passed: [" + result.getMethod().getMethodName() + "]");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.err.println("❌ Failed: [" + result.getMethod().getMethodName() + "]");
        System.err.println("   Error: " + result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("--- 🏁 Test Suite Finished: " + context.getName() + " ---\n");
    }
}
