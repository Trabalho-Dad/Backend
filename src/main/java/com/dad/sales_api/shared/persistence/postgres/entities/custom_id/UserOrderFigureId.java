package com.dad.sales_api.shared.persistence.postgres.entities.custom_id;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserOrderFigureId implements Serializable {

    @Column(name = "id_user_order")
    private Integer idUserOrder;

    @Column(name = "id_figure")
    private Integer idFigure;
}
