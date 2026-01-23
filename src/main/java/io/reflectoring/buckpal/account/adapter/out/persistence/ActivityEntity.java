package io.reflectoring.buckpal.account.adapter.out.persistence;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="activity")
class ActivityEntity {

    private Long id;

    private LocalDateTime timestamp;

    private Long ownerAccountId;

    private Long sourceAccountId;

    private Long tartgetAccountId;

    private Long amount;

}
