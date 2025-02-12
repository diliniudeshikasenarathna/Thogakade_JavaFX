package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String itemCode;
    private String description;
    private String packSize;
    private Double unitPrice;
    private int qtyOnHand;
}
