package model;

public record ContactData(String firstname, String middlename, String lastname, String company, String address,
                          String homePhone, String mobile, String workPhone, String email, String email2,
                          String homepage) {

    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withFirstName(String firstname) {
        return new ContactData(firstname, this.middlename, this.lastname, this.company, this.address, this.homePhone, this.mobile, this.workPhone, this.email, this.email2, this.homepage);
    }

    public ContactData withMiddleName(String middlename) {
        return new ContactData(this.firstname, middlename, this.lastname, this.company, this.address, this.homePhone, this.mobile, this.workPhone, this.email, this.email2, this.homepage);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.firstname, this.middlename, lastname, this.company, this.address, this.homePhone, this.mobile, this.workPhone, this.email, this.email2, this.homepage);
    }

    public ContactData withCompany(String company) {
        return new ContactData(this.firstname, this.middlename, this.lastname, company, this.address, this.homePhone, this.mobile, this.workPhone, this.email, this.email2, this.homepage);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.firstname, this.middlename, this.lastname, this.company, address, this.homePhone, this.mobile, this.workPhone, this.email, this.email2, this.homepage);
    }

    public ContactData withHome(String home) {
        return new ContactData(this.firstname, this.middlename, this.lastname, this.company, this.address, home, this.mobile, this.workPhone, this.email, this.email2, this.homepage);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.firstname, this.middlename, this.lastname, this.company, this.address, this.homePhone, mobile, this.workPhone, this.email, this.email2, this.homepage);
    }

    public ContactData withWork(String work) {
        return new ContactData(this.firstname, this.middlename, this.lastname, this.company, this.address, this.homePhone, this.mobile, work, this.email, this.email2, this.homepage);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.firstname, this.middlename, this.lastname, this.company, this.address, this.homePhone, this.mobile, this.workPhone, email, this.email2, this.homepage);
    }

    public ContactData withEmail2(String email2) {
        return new ContactData(this.firstname, this.middlename, this.lastname, this.company, this.address, this.homePhone, this.mobile, this.workPhone, this.email, email2, this.homepage);
    }

    public ContactData withHomepage(String homepage) {
        return new ContactData(this.firstname, this.middlename, this.lastname, this.company, this.address, this.homePhone, this.mobile, this.workPhone, this.email, this.email2, homepage);
    }
}
