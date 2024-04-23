package com.redis.demo.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

	
	private Long memberId;
	private String name;
	private List<History> historyList = new ArrayList<>();
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class History{
		private Long id;
		private String connectYYYYMMDD;
		private String connectPath;
	}
	
}
