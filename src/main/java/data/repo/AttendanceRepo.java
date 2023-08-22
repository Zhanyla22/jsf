package data.repo;

import data.entity.AttendRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AttendanceRepo extends JpaRepository<AttendRecord, Long> {

    boolean existsByUserEntityIdAndAttendDate(Long id, LocalDate dateTime);

    @Query(value = "SELECT * FROM attendance a WHERE a.user_entity_id = ?1  ORDER BY a.created_date desc LIMIT 1 ", nativeQuery = true)
    Optional<AttendRecord> findLast(Long id);

}
