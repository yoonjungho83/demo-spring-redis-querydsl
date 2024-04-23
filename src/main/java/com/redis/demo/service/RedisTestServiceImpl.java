package com.redis.demo.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.redis.demo.model.dto.ResponseDto;
import com.redis.demo.model.entity.Member;
import com.redis.demo.model.entity.MemberHistory;
import com.redis.demo.model.entity.QMember;
import com.redis.demo.model.entity.QMemberHistory;
import com.redis.demo.repository.interfaces.MemberRepository;
import com.redis.demo.service.interfaces.RedisTestService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RedisTestServiceImpl implements RedisTestService{

	private final MemberRepository repository;
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	JPAQueryFactory query = new JPAQueryFactory(em);
	
	private QMember qMember = QMember.member;
	private QMemberHistory qMemberHistory = QMemberHistory.memberHistory;

    @Override
    @Transactional
    public void joinMember(Member member) {
    	
    	for (int i = 0; i < 10; i++) {
    		MemberHistory mh = MemberHistory.builder().connectPath("test path")
    				.member(member)
    				.connectYYYYMMDD("20240423")
    				.build();
    		member.getMemberHistory().add(mh);
		}
    	repository.save(member);
    }

    @Override
    @CachePut(value = "Member", key = "#memberId", cacheManager = "cacheManager")
    @Transactional
    public ResponseDto updateMember(Member member, Long memberId) {
    	ResponseDto dto = new ResponseDto();
    	repository.save(member);
    	
    	return getResDto(memberId);
    }

    @Transactional
    @Override
    @Cacheable(value = "Member", key = "#memberId", cacheManager = "cacheManager", unless = "#result == null")
    public ResponseDto getMemberInfo(Long memberId) {
    	
    	return getResDto(memberId);
    	
    }

    @Override
    @CacheEvict(value = "Member", key = "#memberId", cacheManager = "cacheManager")
    @Transactional
    public void removeMember(Long memberId) {
    	Optional<Member> opt =  repository.findById(memberId);
    	Member mb = null;
    	if(opt.isPresent()) {
    		mb = opt.get();
    		repository.deleteById(mb.getId());
    	}else {
    		log.info("member is not found");
    	}
    }
    
    
    
    @Transactional
    public ResponseDto getResDto(Long memberId) {
    	log.info("##############################");
    	log.info("###########getMemberInfo######");
    	log.info("##############################");
    	ResponseDto dto = new ResponseDto();
    	Member mb =
    	query.select(qMember)
    		.from(qMember)
    		.leftJoin(qMember.memberHistory , qMemberHistory).fetchJoin()
    		.where(qMember.id.eq(memberId))
    		.fetchOne();
    	dto.setMemberId(mb.getId());
    	dto.setName(mb.getName());
    	mb.getMemberHistory().stream().forEach(x->{
    		ResponseDto.History his = new ResponseDto.History();
    		his.setConnectPath(x.getConnectPath());
    		his.setConnectYYYYMMDD(x.getConnectYYYYMMDD());
    		his.setId(x.getId());
    		dto.getHistoryList().add(his);
    	});
    	
    	List<MemberHistory> hisList = 
    	query.select(qMemberHistory)
    		 .from(qMemberHistory)
    		 .where(qMemberHistory.member.id.eq(memberId))
    		 .fetch();
    	
    	return dto;
    }
}
