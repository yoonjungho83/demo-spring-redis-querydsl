package com.redis.demo.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.redis.demo.model.entity.Member;
@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

	
//	@EntityGraph(attributePaths = {"sampleList"})
//	List<Member> findAllById(String product_id);
	
//	public Member save(Member member);
//	public Member findOne(Long memberId);
//	public void remove(Member member);
}
