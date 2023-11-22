package com.sdjeans.sdjeans_app.C_app.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // DBのテーブルを表す
@Table(name="member")
public class Member {

    @Id
    @Column(name = "member_id") // DBのカラム名指定
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "pw")
    private String pw;

    @Column(name = "address")
    private String address;

    // getter
    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPw() {
        return this.pw;
    }

    public String getAddress() {
        return this.address;
    }

    // setter
    public void setId(String id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
