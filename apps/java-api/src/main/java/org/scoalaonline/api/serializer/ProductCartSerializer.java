package org.scoalaonline.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.scoalaonline.api.model.ProductCart;
import org.scoalaonline.api.model.ProductCartKey;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductCartSerializer extends StdSerializer<List<ProductCart>> {

  public ProductCartSerializer(){
    this(null);
  }

  public ProductCartSerializer(Class<List<ProductCart>> t) {
    super(t);
  }

  @Override
  public void serialize(List<ProductCart> productCarts, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    Map<Long, Long> ids = new HashMap<>();
    for (ProductCart productCart : productCarts){
      ProductCartKey key = productCart.getId();
      ids.put(key.getProductID(), key.getCustomerID());
    }
    jsonGenerator.writeObject(ids);
  }
}
