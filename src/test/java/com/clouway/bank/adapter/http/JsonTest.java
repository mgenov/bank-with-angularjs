package com.clouway.bank.adapter.http;

import com.google.inject.TypeLiteral;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Miroslav Genov (miroslav.genov@clouway.com)
 */
public class JsonTest {

  @Test
  public void deSerializeGenericType() throws Exception {
    Json json = new Json();
    List<User> users = json.in(sourceOf("[{name: \"John\"}]"), new TypeLiteral<List<User>>() {
    });
    assertThat(users.size(), is(1));
    assertThat(users.get(0).name, is(equalTo("John")));
  }

  private InputStream sourceOf(String content) {
    return new ByteArrayInputStream(content.getBytes());
  }

  private static class User {
    private String name;
  }

}