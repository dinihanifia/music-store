package com.test.musicstore.POJO.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "artist")
@Getter
@Setter
public class Artist {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(updatable = false)
    private UUID artistId;
    private String artistName;
    private String artistCountry;
    @OneToMany(mappedBy = "artist")
    @JsonIgnore
    private List<Album> albums;
}
