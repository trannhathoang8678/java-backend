package database.models;

public class ProviderEntity {
    private String id_Provider;
    private String nameOfProvider;
    private String address;
    private String phoneNumber;
    private String taxNumber;

    public ProviderEntity(String id_Provider, String nameOfProvider, String address, String phoneNumber, String taxNumber) {
        this.id_Provider = id_Provider;
        this.nameOfProvider = nameOfProvider;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.taxNumber = taxNumber;
    }

    public ProviderEntity() {
    }

    public String getId_Provider() {
        return id_Provider;
    }

    public void setId_Provider(String id_Provider) {
        this.id_Provider = id_Provider;
    }

    public String getNameOfProvider() {
        return nameOfProvider;
    }

    public void setNameOfProvider(String nameOfProvider) {
        this.nameOfProvider = nameOfProvider;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    @Override
    public String toString() {
        return id_Provider + " : " + nameOfProvider + " at " + address + " with phone number " + phoneNumber
                + "and tax number " + taxNumber;
    }
}
