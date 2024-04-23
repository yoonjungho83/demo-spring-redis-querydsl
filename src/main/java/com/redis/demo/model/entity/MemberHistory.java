package com.redis.demo.model.entity;

import java.io.Serializable;
import java.security.Timestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberHistory implements Serializable{

	private static final long serialVersionUID = 1L; 
	@Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "CONNECT_YYYYMMDD")
    private String connectYYYYMMDD;
 
    @Column(name = "CONNECT_PATH")
    private String connectPath;
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID" , referencedColumnName = "MEMBER_ID")
//	@JsonIgnoreProperties//해당 어노테이션은 무한반복을 안되게 함.
//	@JsonManagedReference // 순환참조 방지
//	@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
	private Member member;
}
