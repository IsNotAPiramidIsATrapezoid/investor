package com.init.piramid.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.init.piramid.entity.Investments;

public interface InvestmentDAO extends JpaRepository<Investments, Long> {

}
