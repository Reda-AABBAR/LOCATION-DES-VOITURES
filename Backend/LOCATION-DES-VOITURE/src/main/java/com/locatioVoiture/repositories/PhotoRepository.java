package com.locatioVoiture.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locatioVoiture.entities.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long>{

}
