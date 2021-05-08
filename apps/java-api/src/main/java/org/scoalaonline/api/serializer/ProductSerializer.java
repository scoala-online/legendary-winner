package org.scoalaonline.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.scoalaonline.api.model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductSerializer extends StdSerializer<List<Product>> {

  public ProductSerializer(){
    this(null);
  }

  public ProductSerializer(Class<List<Product>> t) {
    super(t);
  }

  @Override
  public void serialize(List<Product> products, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    List<Long> ids = new ArrayList<>();
    for (Product product : products){
      ids.add(product.getProductID());
    }
    jsonGenerator.writeObject(ids);
  }
}
