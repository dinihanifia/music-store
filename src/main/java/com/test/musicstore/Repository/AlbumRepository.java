package com.test.musicstore.Repository;
import com.test.musicstore.POJO.Entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AlbumRepository extends JpaRepository<Album, UUID> {
}
