package com.dad.sales_api.shared.entities.custom_id;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CustomerOrderFigureId implements Serializable {

    private Integer idCustomerOrder;

    private Integer idFigure;
}
