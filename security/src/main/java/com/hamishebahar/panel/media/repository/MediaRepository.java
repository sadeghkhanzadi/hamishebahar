package com.hamishebahar.panel.media.repository;

import com.hamishebahar.panel.media.entity.Medias;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MediaRepository extends JpaRepository<Medias , Long> {
    Page<Medias> findAllByNameAndState(String name, String name1, Pageable pageable);

    Page<Medias> findAllByName(String name, Pageable pageable);

    Page<Medias> findAllByState(String name, Pageable pageable);
}
