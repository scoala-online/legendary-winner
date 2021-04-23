package org.scoalaonline.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.scoalaonline.api.model.Product;
import org.scoalaonline.api.model.ProductCart;
import org.scoalaonline.api.model.Store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductCartSerializer extends StdSerializer<List<ProductCart>> {

  public ProductCartSerializer(){
    this(null);
  }

  public ProductCartSerializer(Class<List<ProductCart>> t) {
    super(t);
  }

  @Override
  public void serialize(List<ProductCart> productCarts, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//    List<Long> ids = new ArrayList<>();
//    for (ProductCart productCart : products){
//      ids.add(productCart.getId());
//    }
//    jsonGenerator.writeObject(ids);
  }
}
