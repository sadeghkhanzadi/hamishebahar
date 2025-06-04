package com.hamishebahar.security.panel.media.repository;

import com.hamishebahar.security.commonts.Enums.MediaStates;
import com.hamishebahar.security.panel.media.entity.Medias;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Medias, Long> {
    Page<Medias> findAllByNameAndStates(String name, MediaStates states, Pageable pageable);

    Page<Medias> findAllByName(String name, Pageable pageable);

    Page<Medias> findAllByStates(MediaStates states, Pageable pageable);

    @Query(value = "select M from Medias M where M.is_active = :isActive and M.is_deleted = :isDeleted")
    Page<Medias> findALLByIsActiveAndIsDeleted(@Param("isActive") Boolean isActive,
                                               @Param("isDeleted") Boolean isDeleted,
                                               Pageable pageable);
}
