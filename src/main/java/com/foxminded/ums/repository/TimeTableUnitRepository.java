package com.foxminded.ums.repository;

import com.foxminded.ums.entities.TimeTableUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TimeTableUnitRepository extends JpaRepository<TimeTableUnit, UUID> {
    @Query(value = "SELECT tunit FROM TimeTableUnit tunit " +
            " JOIN tunit.groups grp " +
            " JOIN grp.students stdnt" +
            " WHERE stdnt.id = :id " +
            " AND DAY(tunit.begin) = :pday " +
            " AND YEAR(tunit.begin) = :pyear " +
            " AND MONTH(tunit.begin) = :pmonth")
    List<TimeTableUnit> findByDayForStudent(@Param("id") UUID id,
                                            @Param("pday") Integer pday,
                                            @Param("pmonth") Integer month,
                                            @Param("pyear") Integer year);

    @Query(value = "SELECT tunit FROM TimeTableUnit tunit " +
            " JOIN tunit.groups grp " +
            " JOIN grp.students stdnt" +
            " WHERE stdnt.id = :id " +
            " AND YEAR(tunit.begin) = :pyear " +
            " AND MONTH(tunit.begin) = :pmonth")
    List<TimeTableUnit> findByMonthForStudent(@Param("id") UUID id,
                                              @Param("pyear") Integer year,
                                              @Param("pmonth") Integer month);

    @Query(value = "SELECT tunit FROM TimeTableUnit tunit " +
            " JOIN tunit.lecture lct " +
            " JOIN lct.teacher tchr" +
            " WHERE tchr.id = :id " +
            " AND DAY(tunit.begin) = :pday " +
            " AND YEAR(tunit.begin) = :pyear " +
            " AND MONTH(tunit.begin) = :pmonth")
    List<TimeTableUnit> findByDayForTeacher(@Param("id") UUID id,
                                            @Param("pday") Integer pday,
                                            @Param("pmonth") Integer month,
                                            @Param("pyear") Integer year);

    @Query(value = "SELECT tunit FROM TimeTableUnit tunit " +
            " JOIN tunit.lecture lct " +
            " JOIN lct.teacher tchr" +
            " WHERE tchr.id = :id " +
            " AND YEAR(tunit.begin) = :pyear " +
            " AND MONTH(tunit.begin) = :pmonth")
    List<TimeTableUnit> findByMonthForTeacher(@Param("id") UUID id,
                                              @Param("pyear") Integer year,
                                              @Param("pmonth") Integer month);
}