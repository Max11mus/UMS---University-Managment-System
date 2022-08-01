package com.foxminded.ums.repository;

import com.foxminded.ums.entities.TimeTableUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface TimeTableUnitRepository extends JpaRepository<TimeTableUnit, UUID> {
    @Query(value = "SELECT tunit FROM TimeTableUnit tunit " +
            " JOIN tunit.groups grp " +
            " JOIN grp.students stdnt" +
            " WHERE stdnt.id = :id AND DAY(t.begin) = DAY(:dateTime)")
    List<TimeTableUnit> findByDayForStudent(@Param("id") UUID id, @Param("dateTime") LocalDateTime localDateTime);

//    @Query(value = "SELECT t FROM TimeTableUnit t WHERE MONTH(t.begin) = MONTH(:dateTime)")
//    List<TimeTableUnit> findByMonth(@Param("dateTime") LocalDateTime creationDateTime);
}
//    SELECT x "
//        + "FROM MyInfo x JOIN x.myInfoRequests b "
//        + "WHERE x.status = :status AND b.dataA = :dataA";