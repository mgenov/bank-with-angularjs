package com.clouway.bank.adapter.http;

import com.google.common.base.Charsets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.TypeLiteral;
import com.google.sitebricks.client.Transport;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * @author Miroslav Genov (miroslav.genov@clouway.com)
 */
public final class Json implements Transport {
  private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

  @Override
  public <T> T in(InputStream inputStream, Class<T> aClass) throws IOException {
    return gson.fromJson(new InputStreamReader(inputStream, Charsets.UTF_8), aClass);
  }

  @Override
  public <T> T in(InputStream inputStream, TypeLiteral<T> typeLiteral) throws IOException {
    return (T) gson.fromJson(new InputStreamReader(inputStream, Charsets.UTF_8), typeLiteral.getType());
  }

  @Override
  public <T> void out(OutputStream outputStream, Class<T> aClass, T t) throws IOException {
    OutputStreamWriter writer = new OutputStreamWriter(outputStream, Charsets.UTF_8);
    gson.toJson(t, writer);
    writer.flush();
  }

  @Override
  public String contentType() {
    return "application/json";
  }
}
