package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.map.annotate.JsonSerialize;


@Data
@AllArgsConstructor
@Builder
@JsonSerialize
public class NewOrder {

    private Boolean Complete;
    private Long Id;
    private Long PetId;
    private Long Quantity;
    private String ShipDate;
    private String Status;




}
