package com.github.klucsik.birdnoiseserver.backendserver.repository;

import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {
    Track findByName(String name);
    Track findByTrackNumber(Integer trackNumber);

    Boolean existsByTrackNumber(Integer trackNubmer);
    Boolean existsByName(String name);

}
