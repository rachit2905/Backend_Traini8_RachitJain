package com.org.Traini8.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.org.Traini8.pojo.TrainingCentre;

/**
 * This interface serves as a repository for Training Centre entities and
 * extends JpaRepository to leverage Spring Data JPA's repository support, which
 * provides default implementations for CRUD operations on the TrainingCentre
 * entity.
 */
@Repository
public interface TrainingCenterRepository extends JpaRepository<TrainingCentre, String> {

    /**
     * Custom query method to find Training Centres based on multiple optional
     * search criteria. The method uses a JPQL query with dynamic filters that are
     * applied only if the parameters are not null. The query performs a LEFT JOIN
     * on the courses offered by each centre to optionally filter by courses.
     *
     * @param centerCode   the unique code of the training center; used in a LIKE
     *                     search if not null.
     * @param centerName   the name of the training center; used in a LIKE search if
     *                     not null.
     * @param minCapacity  the minimum student capacity filter; only centers with at
     *                     least this capacity are returned.
     * @param maxCapacity  the maximum student capacity filter; only centers with
     *                     capacity up to this are returned.
     * @param contactEmail the email used for contacting the training center; used
     *                     in a LIKE search if not null.
     * @param contactPhone the phone number for contacting the training center; used
     *                     in a LIKE search if not null.
     * @param city         the city where the training center is located; used in a
     *                     LIKE search if not null.
     * @param state        the state where the training center is located; used in a
     *                     LIKE search if not null.
     * @param pincode      the pin code of the training center's location; matches
     *                     exactly if not null.
     * @param courses      a list of course names; returns centers offering any of
     *                     the courses in this list if not null.
     * @return a list of Training Centres that match the given criteria.
     */
    @Query("""
            SELECT t FROM TrainingCentre t
            LEFT JOIN t.coursesOffered c
            WHERE (:centerCode IS NULL OR LOWER(t.centerCode) LIKE LOWER(CONCAT('%', :centerCode, '%')))
            AND (:centerName IS NULL OR LOWER(t.centerName) LIKE LOWER(CONCAT('%', :centerName, '%')))
            AND (:minCapacity IS NULL OR t.studentCapacity >= :minCapacity)
            AND (:maxCapacity IS NULL OR t.studentCapacity <= :maxCapacity)
            AND (:contactEmail IS NULL OR LOWER(t.contactEmail) LIKE LOWER(CONCAT('%', :contactEmail, '%')))
            AND (:contactPhone IS NULL OR LOWER(t.contactPhone) LIKE LOWER(CONCAT('%', :contactPhone, '%')))
            AND (:city IS NULL OR LOWER(t.address.city) LIKE LOWER(CONCAT('%', :city, '%')))
            AND (:state IS NULL OR LOWER(t.address.state) LIKE LOWER(CONCAT('%', :state, '%')))
            AND (:pincode IS NULL OR t.address.pincode = :pincode)
            AND (:courses IS NULL OR c IN :courses)
            GROUP BY t
            """)
    List<TrainingCentre> findTrainingCentresByMultipleFields(String centerCode, String centerName, Integer minCapacity,
            Integer maxCapacity, String contactEmail, String contactPhone, String city, String state, String pincode,
            List<String> courses);
}
