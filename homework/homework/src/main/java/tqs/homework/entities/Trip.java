package tqs.homework.entities;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.time.ZoneOffset;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String depName;

    @Column(nullable = false)
    private String arrName;
    
    @Column(nullable = false)
    private Timestamp depTime;

    @Column(nullable = false)
    private Timestamp arrTime;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Time duration;

    @Column(nullable = false)
    private List<Long> seats; // pre-formatted seats, based on Rede Expressos' website

    @Column(nullable = false)
    private int price; // in cents

    @OneToMany(mappedBy = "trip")
    private List<Reservation> reservations;

    public Trip() {
    }

    public Trip(String depName, String arrName, Timestamp depTime, Timestamp arrTime, int price) {
        this.arrName = arrName;
        this.depName = depName;
        this.arrTime = arrTime;
        this.depTime = depTime;
        this.seats = new ArrayList<>(53);
        this.price = price;
        this.duration = new Time(arrTime.getTime() - depTime.getTime());
        // The date should only correspond to the day of the trip, not the time
        this.date = new Date(depTime.toLocalDateTime().toLocalDate().atStartOfDay().toEpochSecond(ZoneOffset.UTC) * 1000);
    }
}