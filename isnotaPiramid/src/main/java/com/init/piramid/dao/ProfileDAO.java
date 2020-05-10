package com.init.piramid.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.init.piramid.entity.Profile;

public interface ProfileDAO extends JpaRepository<Profile, Long>{

}
