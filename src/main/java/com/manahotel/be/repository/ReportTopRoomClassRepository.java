package com.manahotel.be.repository;

import com.manahotel.be.model.entity.ReportTopRoomClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReportTopRoomClassRepository extends JpaRepository<ReportTopRoomClass, Long> {

    ReportTopRoomClass findByRoomClassIdAndCreateDateBetween(String roomClassId, Timestamp startDate, Timestamp endDate);

}
