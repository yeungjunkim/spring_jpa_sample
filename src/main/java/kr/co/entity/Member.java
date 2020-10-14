package kr.co.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "MEMBER")

@NamedQuery(
        name = "member.findMemberByName",
        query = "select m from Member m where m.name = :name"
)

@Data
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name= "MEMBER_ID")
    private long id;

    @Column(name= "MEMBER_NAME")
    private String name;

    public Member(String name) {
        this.name = name;
    }
}
