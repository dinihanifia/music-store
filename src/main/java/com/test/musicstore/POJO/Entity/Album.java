package com.test.musicstore.POJO.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "album")
public class Album {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(updatable = false)
    private UUID albumId;
    private String albumName;
    @ManyToOne
    @JoinColumn(name = "artistId")
    private Artist artist;
    private int albumPrice;
    @OneToMany(mappedBy = "album")
    @JsonIgnore
    private List<Sales> sales;
}
