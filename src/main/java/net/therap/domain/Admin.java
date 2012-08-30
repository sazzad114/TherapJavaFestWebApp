package net.therap.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 7/19/12
 * Time: 4:53 PM
 */
@Entity
@Table(name = "ADMIN")
public class Admin implements Serializable {
    private long adminId;
    private String adminName;
    private String adminUserName;
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ADMIN_ID")
    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    @Column(name = "ADMIN_NAME")
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    @Column(name = "ADMIN_USER_NAME")
    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
