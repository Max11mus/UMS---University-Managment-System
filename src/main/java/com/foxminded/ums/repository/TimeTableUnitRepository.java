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
            " AND tunit.begin >= :pstartTime" +
            " AND tunit.begin < :pendTime")
    List<TimeTableUnit> findByBeginBetweenForStudent(@Param("id") UUID id,
                                                     @Param("pstartTime") LocalDateTime startTimeInclude,
                                                     @Param("pendTime") LocalDateTime endTimeExclude);

    @Query(value = "SELECT tunit FROM TimeTableUnit tunit " +
            " JOIN tunit.lecture lct " +
            " JOIN lct.teacher tchr" +
            " WHERE tchr.id = :id " +
            " AND tunit.begin >= :pstartTime" +
            " AND tunit.begin < :pendTime")
    List<TimeTableUnit> findByBeginBetweenForTeacher(@Param("id") UUID id,
                                                     @Param("pstartTime") LocalDateTime startTimeInclude,
                                                     @Param("pendTime") LocalDateTime endTimeExclude);
}