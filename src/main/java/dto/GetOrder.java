package dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Data
@AllArgsConstructor
@Builder
@JsonSerialize
public class GetOrder {


}