package com.skillmentor.service.respositories;

import com.skillmentor.service.entities.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO mentor_subjects (mentor_id, subject_id) SELECT :mentorId, id FROM subjects WHERE id IN :subjectIds " +
            "ON CONFLICT (mentor_id, subject_id) DO NOTHING", nativeQuery = true)
    void addSubjectsToMentorBatch(@Param("mentorId") Long mentorId, @Param("subjectIds") List<Long> subjectIds);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM mentor_subjects WHERE mentor_id = :mentorId AND subject_id IN :subjectIds", nativeQuery = true)
    void removeSubjectsFromMentor(@Param("mentorId") Long mentorId, @Param("subjectIds") List<Long> subjectIds);
}
