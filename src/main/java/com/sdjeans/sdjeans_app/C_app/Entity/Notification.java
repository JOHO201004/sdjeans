package com.sdjeans.sdjeans_app.C_app.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="purchasehistory")
public class Notification {

    @Id
    @Column(name = "member_id")
    private String id;

    @Column(name = "deadline")
    private Date deadline;
}
