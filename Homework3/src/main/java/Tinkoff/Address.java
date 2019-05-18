package Tinkoff;

class Address {

    public Address(String postcode, String country,
                   String region, String city,
                   String street, String house, String apartment) {
        this.postcode = postcode;
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }
    @Override
    public String toString()
    {
        return postcode+", "+country+", "+region+", "+city+", "+street+", "+house+", "+apartment;
    }
    
    private final String postcode;
    private final String country;
    private final String region;
    private final String city;
    private final String street;
    private final String house;
    private final String apartment;
}
