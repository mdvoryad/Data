package htw;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor

public class Product {

    int id;
    String name;
    String size;
    String dough;
    String fill;
    String glasur;
    String extras;
    String description;
    String userName;
}
