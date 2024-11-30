package org.example.evaluations.config;

import com.razorpay.RazorpayClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class BeanTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void testRazorpayBeanPresence() {
        RazorpayClient razorpayClientBean = applicationContext.getBean(RazorpayClient.class);
        assertNotNull(razorpayClientBean, "RazorpayClient bean should be present in the application context");
    }
}
