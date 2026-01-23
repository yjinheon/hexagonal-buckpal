package io.reflectoring.buckpal.account.adapter.out.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {

    @Query("""
            SELECT a FROM ActivityJpaEntity a
            WHERE a.ownerAccountId = :ownerAccountId
            AND a.timestamp >= :since
            """)
    List<ActivityEntity> findByOwnerSince(
            @Param("ownerAccountId") Long ownerAccountId,
            @Param("since") LocalDateTime since);

    @Query("""
            SELECT SUM(a.amount) FROM ActivityJpaEntity a
            WHERE a.ownerAccountId = :ownerAccountId
            AND a.timestamp < :until
            """)
    Long getDepositBalanceUntil(
            @Param("ownerAccountId") Long ownerAccountId,
            @Param("until") LocalDateTime until);

    @Query("""
            SELECT SUM(a.amount) FROM ActivityJpaEntity a
            WHERE a.ownerAccountId = :ownerAccountId
            AND a.timestamp < :until
            """)
    Long getWithdrawalBalanceUntil(
            @Param("ownerAccountId") Long ownerAccountId,
            @Param("until") LocalDateTime until);
}
