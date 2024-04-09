package tqs.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tqs.homework.entities.Trip;
import java.util.List;
import java.util.Date;	

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    public List<Trip> findByArrNameAndDepNameAndDate(String arrName, String depName, Date date);
}
