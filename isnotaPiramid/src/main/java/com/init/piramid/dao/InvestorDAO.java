package com.init.piramid.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.init.piramid.entity.Investor;

public interface InvestorDAO extends JpaRepository<Investor, Long>{

}
