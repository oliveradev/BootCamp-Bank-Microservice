package bootcamp.customerservice.customerservice.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "typeCustomer")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerType {
    @Id
    private String id;
    @Indexed(unique = true)
    @Field(name = "code")
    private String code;
    @Indexed(unique = true)
    private String name;

}
