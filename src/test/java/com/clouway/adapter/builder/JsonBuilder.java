package com.clouway.adapter.builder;

import com.google.gson.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Borislav Gadjev <gadjevb@gmail.com>
 */
@SuppressWarnings("all")
public class JsonBuilder {
  private static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

  public static JsonBuilder aNewJson() {
    return new JsonBuilder(new JsonObject());
  }

  public static JsonBuilder aNewJsonArray() {
    return new JsonBuilder(new JsonArray());
  }

  private JsonElement target;

  public JsonBuilder(JsonElement target) {
    this.target = target;
  }

  public JsonBuilder add(String property, String value) {
    target.getAsJsonObject().addProperty(property, value);
    return this;
  }

  public JsonBuilder add(String property, Long value) {
    target.getAsJsonObject().addProperty(property, value);
    return this;
  }

  public JsonBuilder add(String property, Integer value) {
    target.getAsJsonObject().addProperty(property, value);
    return this;
  }

  public JsonBuilder add(String property, Double value){
    target.getAsJsonObject().addProperty(property, value);
    return this;
  }

  public JsonBuilder add(String property, boolean value) {
    target.getAsJsonObject().addProperty(property, value);
    return this;
  }

  public JsonBuilder add(String property, JsonBuilder value) {
    target.getAsJsonObject().add(property, value.asJsonElement());
    return this;
  }

  public JsonBuilder add(String property, LocalDate value) {
    JsonObject o = new JsonObject();
    Date asDate = Date.from(LocalDate.of(2017, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    target.getAsJsonObject().add(property, new JsonPrimitive(dateFormat.format(asDate)));
    return this;
  }

  public JsonBuilder add(String property, Map<String, String> object) {
    JsonObject o = new JsonObject();
    for (String key : object.keySet()) {
      o.addProperty(key, object.get(key));
    }
    target.getAsJsonObject().add(property, o);
    return this;
  }

  public JsonBuilder add(String property, Set<?> values) {
    JsonArray array = new JsonArray();
    for (Object each : values) {
      if (each instanceof Long) {
        array.add(new JsonPrimitive((Long) each));
      }
      if (each instanceof String) {
        array.add(new JsonPrimitive((String) each));
      }
      if (each instanceof Boolean) {
        array.add(new JsonPrimitive((Boolean) each));
      }

    }
    target.getAsJsonObject().add(property, array);

    return this;
  }

  public JsonBuilder add(String property, List<String> values) {
    JsonArray array = new JsonArray();
    for (String each : values) {
      array.add(new JsonPrimitive(each));
    }
    target.getAsJsonObject().add(property, array);
    return this;
  }

  public JsonBuilder addIntegersAsList(String property, List<Integer> values) {
    JsonArray array = new JsonArray();
    for (Integer each : values) {
      array.add(new JsonPrimitive(each));
    }
    target.getAsJsonObject().add(property, array);
    return this;
  }

  public JsonBuilder withElements(JsonBuilder... builders) {
    for (JsonBuilder each : builders) {
      target.getAsJsonArray().add(each.target);
    }

    return this;
  }

  public JsonBuilder withElements(String... values) {
    for (String each : values) {
      target.getAsJsonArray().add(each);
    }
    return this;
  }

  public JsonBuilder withElements(Integer... values) {
    for (Integer each : values) {
      target.getAsJsonArray().add(each);
    }
    return this;
  }

  public JsonElement asJsonElement() {
    return target;
  }


  public String build() {
    return GSON.toJson(target);
  }



}
