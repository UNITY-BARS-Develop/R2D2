package com.unitybars.r2d2;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by oleg.nestyuk
 * Date: 12-Dec-16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public abstract class AbstractTest {
}
