package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.MembreOutil;
import com.example.demo.entities.OutilMembreId;

@Repository
public interface MemberOutilRepository extends JpaRepository<MembreOutil, OutilMembreId> {
	@Query("select m from MembreOutil m where membre_id=:x")
	List<MembreOutil> findMemberOutilByMemberId(@Param("x") Long membreId);
	@Transactional
	@Modifying
	@Query(value = "DELETE from Membre_Outil where membre_id=?1 and outil_id=?2",nativeQuery = true)
    void desaffecterMembreFromOutil(Long membreId,Long outilId);
	@Query("select m from MembreOutil m where outil_id=:x")
	List<MembreOutil> findMembreByOutilId(@Param("x") Long ouId);
}
