package com.redis.demo.repository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;

import com.redis.demo.model.entity.Member;
import com.redis.demo.repository.interfaces.MemberRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
//
//@Repository
//@RequiredArgsConstructor
public class RedisRepository {//implements MemberRepository
	
//	private final EntityManager em;
//
//	@Override
//    public Member save(Member member) {
//        if (member.getId() == null) {
//            em.persist(member);
//        } 
//        else 
//        {
//            Member findMember = em.find(Member.class, member.getId());
//            findMember.setName(member.getName());
//        }
//
//        return member;
//    }
//
//    @Override
//    public Member findOne(Long memberId) {
//        return em.find(Member.class, memberId);
//    }
//
//    @Override
//    public void remove(Member member) {
//        em.remove(member);
//    }
	
	

	

}
