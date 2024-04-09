package tqs.homework.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int seat;

    @Column(nullable = false)
    private int price; // in cents

    @ManyToOne
    private Trip trip;

    public Reservation() {
    }

    public Reservation(String name, String email, int seat, Trip trip, int price) {
        this.name = name;
        this.email = email;
        this.seat = seat;
        this.trip = trip;
        this.price = price;
    }
}
