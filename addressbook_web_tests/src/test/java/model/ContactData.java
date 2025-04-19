package model;

public record ContactData(String id, String firstname, String middlename, String lastname, String company,
                          String address,
                          String homePhone, String mobile, String workPhone, String email, String email2,
                          String homepage, String photo) {

    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstname, this.middlename, this.lastname, this.company, this.address, this.homePhone, this.mobile, this.workPhone, this.email, this.email2, this.homepage, this.photo);
    }

    public ContactData withFirstName(String firstname) {
        return new ContactData(this.id, firstname, this.middlename, this.lastname, this.company, this.address, this.homePhone, this.mobile, this.workPhone, this.email, this.email2, this.homepage, this.photo);
    }

    public ContactData withMiddleName(String middlename) {
        return new ContactData(this.id, this.firstname, middlename, this.lastname, this.company, this.address, this.homePhone, this.mobile, this.workPhone, this.email, this.email2, this.homepage, this.photo);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.id, this.firstname, this.middlename, lastname, this.company, this.address, this.homePhone, this.mobile, this.workPhone, this.email, this.email2, this.homepage, this.photo);
    }

    public ContactData withCompany(String company) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, company, this.address, this.homePhone, this.mobile, this.workPhone, this.email, this.email2, this.homepage, this.photo);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.company, address, this.homePhone, this.mobile, this.workPhone, this.email, this.email2, this.homepage, this.photo);
    }

    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.company, this.address, home, this.mobile, this.workPhone, this.email, this.email2, this.homepage, this.photo);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.company, this.address, this.homePhone, mobile, this.workPhone, this.email, this.email2, this.homepage, this.photo);
    }

    public ContactData withWork(String work) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.company, this.address, this.homePhone, this.mobile, work, this.email, this.email2, this.homepage, this.photo);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.company, this.address, this.homePhone, this.mobile, this.workPhone, email, this.email2, this.homepage, this.photo);
    }

    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.company, this.address, this.homePhone, this.mobile, this.workPhone, this.email, email2, this.homepage, this.photo);
    }

    public ContactData withHomepage(String homepage) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.company, this.address, this.homePhone, this.mobile, this.workPhone, this.email, this.email2, homepage, this.photo);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.company, this.address, this.homePhone, this.mobile, this.workPhone, this.email, this.email2, this.homepage, photo);
    }
}
