package Tinkoff;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class User {

    private final String firstName;
    private final String secondName;
    private final  String middleName;
    private final String gender;
    private final int age;
    private final Date birthDay;
    private final String birthplace;
    private final Address address;

    public User(String firstName, String secondName,
                String middleName, String gender,
                String birthplace, Address address) {

        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.gender = gender;
        this.birthDay = generateRandomDate();
        this.birthplace = birthplace;
        this.address = address;
        this.age=calculateAge(this.birthDay);
    }
    public String[] toStringArray()
    {
        SimpleDateFormat format = new SimpleDateFormat("DD/MM/YYYY");
        return new String[]{firstName,secondName,middleName,gender,Integer.toString(age),format.format(birthDay),birthplace,address.toString()};
    }
    private int calculateAge(Date birthDate) {

        Calendar dob = Calendar.getInstance();
        dob.setTime(birthDate);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) <= dob.get(Calendar.DAY_OF_YEAR))
            age--;
        return age;
    }
    private Date generateRandomDate(){

        Calendar cal=Calendar.getInstance();

        cal.set(Calendar.YEAR, 1900);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date begin = cal.getTime();

        cal.setTime(begin);
        Long value1 = cal.getTimeInMillis();

        Date current=new Date();
        cal.setTime(current);
        Long value2 = cal.getTimeInMillis();

        long value3 = (long)(value1 + Math.random()*(value2 - value1));
        cal.setTimeInMillis(value3);
        return  cal.getTime();
    }

}
