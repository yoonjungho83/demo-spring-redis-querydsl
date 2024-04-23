package com.redis.demo.service.interfaces;

import com.redis.demo.model.dto.ResponseDto;
import com.redis.demo.model.entity.Member;

public interface RedisTestService {
	public void joinMember(Member member);
	public ResponseDto updateMember(Member member, Long memberId);
    public ResponseDto getMemberInfo(Long memberId);
    public void removeMember(Long memberId);
}
