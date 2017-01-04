package com.clouway.bank;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Miroslav Genov (miroslav.genov@clouway.com)
 */
public class SampleTest {

  @Test
  public void happyPath() throws Exception {
    assertThat(true, is(true));
  }
}
