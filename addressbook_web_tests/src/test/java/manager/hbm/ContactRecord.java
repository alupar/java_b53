package manager.hbm;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")
public class ContactRecord {
    @Id
    public int id;
    public String firstname;
    public String lastname;
    public String address;
    public String email;
    public String middlename = "";
    public String company = "";
    public String title = "";
    public String home;
    public String mobile;
    public String work;
    public String fax = "";
    public String email2;
    public String email3;
    public String homepage = "";
    public String nickname = "";

    public ContactRecord() {
    }

    public ContactRecord(int id, String firstname, String lastname, String address, String home, String mobile, String work, String email, String email2, String email3) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.home = home;
        this.mobile = mobile;
        this.work = work;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
    }

}
