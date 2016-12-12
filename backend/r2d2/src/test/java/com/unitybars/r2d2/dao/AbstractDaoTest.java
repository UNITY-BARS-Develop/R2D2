package com.unitybars.r2d2.dao;

import com.unitybars.r2d2.AbstractTest;
import org.springframework.test.annotation.DirtiesContext;

/**
 * Created by oleg.nestyuk
 * Date: 12-Dec-16.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class AbstractDaoTest extends AbstractTest {
}
