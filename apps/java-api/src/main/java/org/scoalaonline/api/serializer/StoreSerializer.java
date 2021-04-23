package org.scoalaonline.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.scoalaonline.api.model.Store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoreSerializer extends StdSerializer<List<Store>> {

  public StoreSerializer(){
    this(null);
  }

  public StoreSerializer(Class<List<Store>> t) {
    super(t);
  }

  @Override
  public void serialize(List<Store> stores, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    List<Long> ids = new ArrayList<>();
    for (Store store : stores){
      ids.add(store.getStoreID());
    }
    jsonGenerator.writeObject(ids);
  }
}
