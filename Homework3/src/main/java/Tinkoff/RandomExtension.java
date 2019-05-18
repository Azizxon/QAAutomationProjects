package Tinkoff;

import java.util.Random;

class RandomExtension {
    public static int getRandomNumber(int max,int min)
    {
        Random random=new Random();
        return random.nextInt(max-min)+min;
    }

    public static int getRandomNumber(int max)
    {
        Random random=new Random();
        return random.nextInt(max);
    }

}
