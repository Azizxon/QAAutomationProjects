package Tinkoff;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

import static Tinkoff.RandomExtension.getRandomNumber;

public class UserRepository implements IUserRepository {

    public UserRepository() throws IOException {
        properties=new Properties();
        properties.load(new FileInputStream("src/main/resources/config.properties"));
    }
    public User[] getAllUsers() {

        try {
            User[] users=initUserArray();
            String[] firstNames=getPropertyArray("firstNamesPath");
            String[] secondNames=getPropertyArray("secondNamesPath");
            String[] middleNames=getPropertyArray("middleNamesPath");
            String[] genders=getPropertyArray("gendersPath");

            if (Objects.requireNonNull(firstNames).length!= Objects.requireNonNull(secondNames).length)
                throw new IllegalArgumentException("Count of first Names, second Names, Middle Names, genders must be equal");
            if (firstNames.length!= Objects.requireNonNull(middleNames).length)
                throw new IllegalArgumentException("Count of first Names, second Names, Middle Names, genders must be equal");
            if (firstNames.length!= Objects.requireNonNull(genders).length)
                throw new IllegalArgumentException("Count of first Names, second Names, Middle Names, genders must be equal");


            String[] birthPlaces=getPropertyArray("birthPlacesPath");

            Address[] addresses=initAddressArray(Objects.requireNonNull(users).length);

            for (int i=0; i<users.length;i++)
            {
                int selectedLine=getRandomNumber(firstNames.length);
                String firstName=firstNames[selectedLine];
                String secondName=secondNames[selectedLine];
                String middleName=middleNames[selectedLine];
                String gender=genders[selectedLine];

                String birthPlace=birthPlaces[getRandomNumber(Objects.requireNonNull(birthPlaces).length)];
                Address address=addresses[getRandomNumber(addresses.length)];

                users[i]=new User(firstName,secondName,middleName,gender,birthPlace,address);
            }

            return users;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
       return null;
    }

    private String[] getPropertyArray(String propertyName) {
        try {

            String path=properties.getProperty(propertyName);
            String[] properties = Files.readAllLines(Paths.get(path)).toArray(new String[0]);

            if (properties.length == 0) throw new NullPointerException();

            return properties;

        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private User[] initUserArray()
    {
         try {
            String maxCountUsersString=properties.getProperty("maxCountUsers");
            String minCountUsersString=properties.getProperty("minCountUsers");

            if(maxCountUsersString==null)
                throw new NullPointerException("The maximum number of users is not specified in the properties");
            if(minCountUsersString==null)
                throw new NullPointerException("The minimum number of users is not specified in the properties");

            int maxCountUsers= Integer.parseInt(maxCountUsersString);
            int minCountUsers= Integer.parseInt(minCountUsersString);

            if(maxCountUsers<0)
                throw new IllegalArgumentException ("Must be a non-negative integer");
            if(minCountUsers<0)
                throw new IllegalArgumentException ("Must be a non-negative integer");
            if (maxCountUsers<minCountUsers)
                throw new IllegalArgumentException ("Max must be bigger than min count");

            int countUsers=getRandomNumber(maxCountUsers,minCountUsers);
             return new User[countUsers];
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;

    }

    private  Address[] initAddressArray(int countAddresses) {

        String[]  countries=getPropertyArray("countriesPath");
        String[] regions=getPropertyArray("regionsPath");
        String[] cities=getPropertyArray("citiesPath");
        String[] streets=getPropertyArray("streetsPath");

        Address[] addresses=new Address[countAddresses];

        for (int i=0; i<addresses.length;i++){

            String postcode=String.format("%06d",getRandomNumber(1000000));
            String country=countries[getRandomNumber(Objects.requireNonNull(countries).length)];
            String region=regions[getRandomNumber(Objects.requireNonNull(regions).length)];
            String city=cities[getRandomNumber(Objects.requireNonNull(cities).length)];
            String street=streets[getRandomNumber(Objects.requireNonNull(streets).length)];
            String house=String.valueOf(getRandomNumber(100,1))+(char) (getRandomNumber(26) + 'a');
            String apartment=String.valueOf(getRandomNumber(100,1));
            addresses[i]=new Address(postcode,country,region,city,street,house,apartment);
        }

        return addresses;
    }

    private final Properties properties;
}
