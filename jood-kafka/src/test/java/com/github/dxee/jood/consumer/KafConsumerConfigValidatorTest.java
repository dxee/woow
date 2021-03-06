package com.github.dxee.jood.consumer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Properties;

import static com.github.dxee.jood.consumer.KafConsumerConfigValidator.*;
import static org.apache.kafka.clients.consumer.ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG;

@RunWith(MockitoJUnitRunner.class)
public class KafConsumerConfigValidatorTest {
    private Properties testProps;

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Rule
    public TestName name = new TestName();

    @Before
    public void printTestHeader() {
        System.out.println("\n=======================================================");
        System.out.println("  Running Test : " + name.getMethodName());
        System.out.println("=======================================================\n");
    }

    @Before
    public void setUp() {
        testProps = new Properties();
        testProps.setProperty(ENABLE_AUTO_COMMIT_CONFIG, "false");
    }

    @Test
    public void is_validate() {
        validate(testProps);
    }

    @Test
    public void autoCommit_true() {
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage(AUTO_COMMIT_ENABLED_MSG);

        testProps.setProperty(ENABLE_AUTO_COMMIT_CONFIG, "true");
        validate(testProps);
    }

    @Test
    public void properties_null() {
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage(NULL_PROPS_MESSAGE);

        validate(null);
    }
}
