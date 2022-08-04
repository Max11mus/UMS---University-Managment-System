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
            " WHERE stdnt.id = :id " +
            " AND YEAR(tunit.begin) = YEAR(:pdate) " +
            " AND MONTH(tunit.begin) = MONTH(:pdate) " +
            " AND DAY(tunit.begin) = DAY(:pdate)")
    List<TimeTableUnit> findByDayForStudent(@Param("id") UUID id,
                                            @Param("pdate") LocalDateTime day);

    @Query(value = "SELECT tunit FROM TimeTableUnit tunit " +
            " JOIN tunit.groups grp " +
            " JOIN grp.students stdnt" +
            " WHERE stdnt.id = :id " +
            " AND YEAR(tunit.begin) = YEAR(:pdate) " +
            " AND MONTH(tunit.begin) = MONTH(:pdate)")
    List<TimeTableUnit> findByMonthForStudent(@Param("id") UUID id,
                                              @Param("pdate") LocalDateTime month);

    @Query(value = "SELECT tunit FROM TimeTableUnit tunit " +
            " JOIN tunit.lecture lct " +
            " JOIN lct.teacher tchr" +
            " WHERE tchr.id = :id " +
            " AND YEAR(tunit.begin) = YEAR(:pdate) " +
            " AND MONTH(tunit.begin) = MONTH(:pdate) " +
            " AND DAY(tunit.begin) = DAY(:pdate)")
    List<TimeTableUnit> findByDayForTeacher(@Param("id") UUID id,
                                            @Param("pdate") LocalDateTime day);

    @Query(value = "SELECT tunit FROM TimeTableUnit tunit " +
            " JOIN tunit.lecture lct " +
            " JOIN lct.teacher tchr" +
            " WHERE tchr.id = :id " +
            " AND YEAR(tunit.begin) = YEAR(:pdate) " +
            " AND MONTH(tunit.begin) = MONTH(:pdate)")
    List<TimeTableUnit> findByMonthForTeacher(@Param("id") UUID id,
                                              @Param("pdate") LocalDateTime month);
}